package com.mf.blackjack;

import java.util.Random;

import com.mf.evolution.Dna;
import com.mf.evolution.DnaBuilder;
import com.mf.utils.Utils;

public class BlackjackDnaBuilder implements DnaBuilder {
    
    @Override
    public Dna random() {
        BlackjackDna dna = new BlackjackDna();
        for (int i = 4; i <= 21; i++) {
            for (Card card : Card.values()) {
                dna.getHard().put(new BlackjackDna.State(i, card), BlackjackNucleotide.randomSoftHard());
            }
        }
        for (int i = 12; i <= 21; i++) {
            for (Card card : Card.values()) {
                dna.getSoft().put(new BlackjackDna.State(i, card), BlackjackNucleotide.randomSoftHard());
            }
        }
        for (int i = 4; i <= 22; i += 2) {
            for (Card card : Card.values()) {
                dna.getPairs().put(new BlackjackDna.State(i, card), BlackjackNucleotide.randomPairs());
            }
        }
        return dna;
    }

    @Override
    public Dna mutate(Dna dna) {
        BlackjackDna blackjackDna = (BlackjackDna) dna;
        double v = new Random().nextDouble();
        if (v < 0.4) {
            BlackjackDna.State state = Utils.random(blackjackDna.getHard().keySet());
            blackjackDna.getHard().put(state, BlackjackNucleotide.randomSoftHard());
        } else if (v < 0.7) {
            BlackjackDna.State state = Utils.random(blackjackDna.getSoft().keySet());
            blackjackDna.getSoft().put(state, BlackjackNucleotide.randomSoftHard());
        } else {
            BlackjackDna.State state = Utils.random(blackjackDna.getPairs().keySet());
            blackjackDna.getPairs().put(state, BlackjackNucleotide.randomPairs());
        }
        return blackjackDna;
    }

    @Override
    public Dna crossover(Dna mother, Dna father) {
        BlackjackDna dna = new BlackjackDna();
        for (int i = 4; i <= 21; i++) {
            for (Card card : Card.values()) {
                BlackjackDna.State state = new BlackjackDna.State(i, card);
                dna.getHard().put(state, random(mother, father).getHard().get(state));
            }
        }
        for (int i = 12; i <= 21; i++) {
            for (Card card : Card.values()) {
                BlackjackDna.State state = new BlackjackDna.State(i, card);
                dna.getSoft().put(
                        state, random(mother, father).getSoft().get(state)
                );
            }
        }
        for (int i = 4; i <= 22; i += 2) {
            for (Card card : Card.values()) {
                BlackjackDna.State state = new BlackjackDna.State(i, card);
                dna.getPairs().put(state, random(mother, father).getPairs().get(state));
            }
        }
        return dna;
    }

    private BlackjackDna random(Dna mother, Dna father) {
        return (BlackjackDna) (new Random().nextBoolean() ? mother : father);
    }
}
