package com.mf.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.mf.utils.Utils;

public enum Card {
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 1, 11);

    private final List<Integer> values = new ArrayList<>();
    private final String name;

    Card(String name, int... value) {
        this.name = name;
        for (int v : value) {
            values.add(v);
        }
    }
    
    public static Card random() {
        return Utils.random(values());
    }

    public List<Integer> getValues() {
        return values;
    }

    public String getName() {
        return name;
    }
}
