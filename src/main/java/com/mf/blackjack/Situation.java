package com.mf.blackjack;

import java.util.HashSet;
import java.util.Set;

public class Situation {

    private Set<Card> hand = new HashSet<>();
    
    private Card dealer;

    public Set<Card> getHand() {
        return hand;
    }

    public void setHand(Set<Card> hand) {
        this.hand = hand;
    }

    public Card getDealer() {
        return dealer;
    }

    public void setDealer(Card dealer) {
        this.dealer = dealer;
    }
}
