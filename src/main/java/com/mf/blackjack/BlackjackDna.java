package com.mf.blackjack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

import com.mf.evolution.Dna;

public class BlackjackDna extends Dna {

    public static class State {
        private int myCount;
        private Card dealer;
        
        public State(int myCount, Card dealer) {
            this.myCount = myCount;
            this.dealer = dealer;
        }

        @Override
        public boolean equals(Object obj) {
            State other = (State) obj;
            return myCount == other.myCount && dealer == other.dealer;
        }

        @Override
        public int hashCode() {
            return dealer.hashCode() + 31 * myCount;
        }
    }
    
    private Map<State, BlackjackNucleotide> hard = new HashMap<>();

    private Map<State, BlackjackNucleotide> soft = new HashMap<>();

    private Map<State, BlackjackNucleotide> pairs = new HashMap<>();
    
    public Map<State, BlackjackNucleotide> getHard() {
        return hard;
    }

    public Map<State, BlackjackNucleotide> getSoft() {
        return soft;
    }

    public Map<State, BlackjackNucleotide> getPairs() {
        return pairs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Hard\n");
        sb.append("  ");
        for (Card c : Card.values()) {
            sb.append(String.format("%1$3s", c.getName()));
        }
        sb.append("\n");
        for (int i = 4; i <= 21; i++) {
            sb.append(String.format("%1$2s", i));
            for (Card card : Card.values()) {
                String name = hard.get(new State(i, card)).getName();
                sb.append(String.format("%1$3s", name));
            }
            sb.append("\n");
        }

        sb.append("Soft\n");
        sb.append("  ");
        for (Card c : Card.values()) {
            sb.append(String.format("%1$3s", c.getName()));
        }
        sb.append("\n");
        for (int i = 12; i <= 21; i++) {
            sb.append(String.format("%1$2s", i));
            for (Card card : Card.values()) {
                String name = soft.get(new State(i, card)).getName();
                sb.append(String.format("%1$3s", name));
            }
            sb.append("\n");
        }

        sb.append("Pairs\n");
        sb.append("  ");
        for (Card c : Card.values()) {
            sb.append(String.format("%1$3s", c.getName()));
        }
        sb.append("\n");
        for (int i = 4; i <= 22; i += 2) {
            sb.append(String.format("%1$2s", i));
            for (Card card : Card.values()) {
                String name = pairs.get(new State(i, card)).getName();
                sb.append(String.format("%1$3s", name));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static BlackjackDna fromFile(String filename) {
        BlackjackDna dna = new BlackjackDna();
        List<String> list = getLines(filename);
        Iterator<String> iterator = list.iterator();
        if (!iterator.hasNext()) {
            throw new IllegalStateException();
        }
        String line = iterator.next();
        if (line.equals("Hard")) {
            if (!iterator.hasNext()) {
                throw new IllegalStateException();
            }
            iterator.next();
            Map<State, BlackjackNucleotide> map = dna.getHard();
            while (iterator.hasNext() && !(line = iterator.next()).equals("Soft")) {
                fillMap(line, map);
            }
        }
        if (line.equals("Soft")) {
            if (!iterator.hasNext()) {
                throw new IllegalStateException();
            }
            iterator.next();
            Map<State, BlackjackNucleotide> map = dna.getSoft();
            while (iterator.hasNext() && !(line = iterator.next()).equals("Pairs")) {
                fillMap(line, map);
            }
        }
        if (line.equals("Pairs")) {
            if (!iterator.hasNext()) {
                throw new IllegalStateException();
            }
            iterator.next();
            Map<State, BlackjackNucleotide> map = dna.getPairs();
            while (iterator.hasNext()) {
                line = iterator.next();
                fillMap(line, map);
            }
        }
        return dna;
    }

    private static void fillMap(String line, Map<State, BlackjackNucleotide> map) {
        String[] actions = line.split("  ");
        int i = 0;
        int cardCount = Integer.parseInt(actions[i].trim());
        for (Card c : Card.values()) {
            i++;
            String action = actions[i];
            BlackjackNucleotide nucleotide = BlackjackNucleotide.getByName(action);
            map.put(new State(cardCount, c), nucleotide);
        }
    }

    private static List<String> getLines(String filename) {
        List<String> list = new ArrayList<>();
        File file = new File(filename);
        try {
            try (Stream<String> lines = Files.lines(file.toPath())) {
                lines.forEachOrdered(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        list.add(s);
                    }
                });
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return list;
    }
}
