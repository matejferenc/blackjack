package com.mf.blackjack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.mf.evolution.Nucleotide;
import com.mf.utils.Utils;

public enum BlackjackNucleotide implements Nucleotide {

    HIT("h"),
    DOUBLE_HIT("H"),
    STAY("s"),
    DOUBLE_STAY("S"),
    SPLIT("p"),
    SURRENDER_HIT("r");
    
    public static final Set<BlackjackNucleotide> SOFT_HARD = new HashSet<>(Arrays.asList(HIT, DOUBLE_HIT, STAY, DOUBLE_STAY, SURRENDER_HIT));
    public static final Set<BlackjackNucleotide> PAIRS = new HashSet<>(Arrays.asList(HIT, DOUBLE_HIT, STAY, DOUBLE_STAY, SPLIT, SURRENDER_HIT));
    
    private final String name;

    BlackjackNucleotide(String name){
        this.name = name;
    }
    
    public static BlackjackNucleotide randomSoftHard() {
        return Utils.random(SOFT_HARD);
    }

    public static BlackjackNucleotide randomPairs() {
        return Utils.random(PAIRS);
    }

    public String getName() {
        return name;
    }
    
    public static BlackjackNucleotide getByName(String name) {
        for (BlackjackNucleotide n : values()) {
            if (n.name.equals(name)) {
                return n;
            }
        }
        throw new IllegalArgumentException();
    }
}
