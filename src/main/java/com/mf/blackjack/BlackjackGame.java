package com.mf.blackjack;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    private BlackjackDna dna;
    private Card dealer = Card.random();
    private List<Card> dealerCards;

    public BlackjackGame(BlackjackDna dna) {
        this.dna = dna;
    }

    public List<GameResult> play() {
        List<Card> hand = new ArrayList<>();
        hand.add(Card.random());
        hand.add(Card.random());

        boolean surrenderAllowed = dealer != Card.ACE;
        return play(hand, true, surrenderAllowed);
    }

    private List<GameResult> play(List<Card> hand, boolean doubleAllowed, boolean surrenderAllowed) {
        List<GameResult> result = new ArrayList<>();
        if (new Sums(hand).getMinimum() > 21) {
            result.add(GameResult.LOSE);
            return result;
        }
        UserAction action = getUserAction(hand, doubleAllowed, surrenderAllowed);
        switch (action) {
            case SPLIT:
                List<Card> hand1 = new ArrayList<Card>(){{ add(hand.get(0)); add(Card.random()); }};
                List<Card> hand2 = new ArrayList<Card>(){{ add(hand.get(1)); add(Card.random()); }};
                if (hand.get(0) == Card.ACE && hand.get(1) == Card.ACE) {
                    result.add(evaluateGame(hand1, false));
                    result.add(evaluateGame(hand2, false));
                    return result;
                } else {
                    result.addAll(play(hand1, true, surrenderAllowed));
                    result.addAll(play(hand2, true, surrenderAllowed));
                    return result;
                }
            case SURRENDER:
                return new ArrayList<GameResult>(){{ add(GameResult.SURRENDER); }};
            case DOUBLE:
                hand.add(Card.random());
                result.add(evaluateGame(hand, true));
                return result;
            case HIT:
                hand.add(Card.random());
                return play(hand, false, false);
            case STAY:
                result.add(evaluateGame(hand, false));
                return result;
            default: throw new IllegalStateException();
        }
    }

    private GameResult evaluateGame(List<Card> hand, boolean doubled) {
        GameResult result;
        Sums playerSums = new Sums(hand);
        if (playerSums.getMinimum() > 21) {
            result = doubled ? GameResult.DOUBLE_LOSE : GameResult.LOSE;
            return result;
        }
        if (dealerCards == null) {
            dealerCards = dealerCards();
        }
        Sums dealerSums = new Sums(dealerCards);
        int dealerValue = dealerSums.getMaximumLessThan21();
        int handValue = playerSums.getMaximumLessThan21();
        if (playerSums.getMinimum() > 21) {
            result = doubled ? GameResult.DOUBLE_LOSE : GameResult.LOSE;
        } else if (playerSums.isBlackjack() && !dealerSums.isBlackjack()) {
            result = GameResult.BLACKJACK;
        } else if (dealerValue > 21) {
            result = doubled ? GameResult.DOUBLE_WIN : GameResult.WIN;
        } else if (handValue > dealerValue) {
            result = doubled ? GameResult.DOUBLE_WIN : GameResult.WIN;
        } else if (handValue < dealerValue) {
            result = doubled ? GameResult.DOUBLE_LOSE : GameResult.LOSE;
        } else if (dealerSums.isBlackjack()) {
            result = doubled ? GameResult.DOUBLE_LOSE : GameResult.LOSE;
        } else if (dealerValue == handValue) {
            result = GameResult.DRAW;
        } else {
            throw new IllegalStateException();
        }
        return result;
    }

    private List<Card> dealerCards() {
        List<Card> dealerCards = new ArrayList<>();
        dealerCards.add(dealer);
        dealerCards.add(Card.random());
        int dealerValue;
        while ((dealerValue = new Sums(dealerCards).getMaximumLessThan21()) < 17 && dealerValue != 0) {
            dealerCards.add(Card.random());
        }
        return dealerCards;
    }

    public enum GameResult {
        WIN,
        DOUBLE_WIN,
        LOSE,
        DOUBLE_LOSE,
        DRAW,
        SURRENDER,
        BLACKJACK
    }

    private enum UserAction {
        HIT,
        STAY,
        DOUBLE,
        SURRENDER,
        SPLIT
    }

    private UserAction getUserAction(List<Card> hand, boolean doubleAllowed, boolean surrenderAllowed) {
        Sums sums = new Sums(hand);
        BlackjackNucleotide nucleotide;
        if (isPair(hand)) {
            nucleotide = dna.getPairs().get(new BlackjackDna.State(sums.getMaximum(), dealer));
        } else if (sums.isSoft()) {
            nucleotide = dna.getSoft().get(new BlackjackDna.State(sums.getMaximumLessThan21(), dealer));
        } else {
            nucleotide = dna.getHard().get(new BlackjackDna.State(sums.getMinimum(), dealer));
        }
        if (nucleotide == null) {
            throw new IllegalStateException();
        }
        switch (nucleotide) {
            case DOUBLE_HIT:
                return doubleAllowed ? UserAction.DOUBLE : UserAction.HIT;
            case DOUBLE_STAY:
                return doubleAllowed ? UserAction.DOUBLE : UserAction.STAY;
            case HIT:
                return UserAction.HIT;
            case STAY:
                return UserAction.STAY;
            case SPLIT:
                return UserAction.SPLIT;
            case SURRENDER_HIT:
                return surrenderAllowed ? UserAction.SURRENDER : UserAction.HIT;
            default:
                throw new IllegalStateException();
        }
    }

    private boolean isPair(List<Card> hand) {
        return hand.size() == 2 && hand.get(0) == hand.get(1);
    }
}
