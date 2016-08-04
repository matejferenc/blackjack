package com.mf.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sums {

    private List<Integer> sums = new ArrayList<>();

    public Sums(List<Card> hand) {
        for (Card c : hand) {
            this.add(c.getValues());
        }
    }

    public void add(List<Integer> options) {
        if (sums.size() == 0) {
            sums.addAll(options);
            return;
        }
        if (options.size() == 1) {
            for (int i = 0; i < sums.size(); i++) {
                sums.set(i, sums.get(i) + options.get(0));
            }
        } else if (options.size() == 2) {
            int originalSize = sums.size();
            for (int i = 0; i < originalSize; i++) {
                sums.add(sums.get(i) + options.get(1));
                sums.set(i, sums.get(i) + options.get(0));
            }
        } else {
            throw new IllegalStateException();
        }
    }

    public int getMaximum() {
        Collections.sort(sums);
        return sums.get(sums.size() - 1);
    }

    public int getMinimum() {
        Collections.sort(sums);
        return sums.get(0);
    }

    public boolean isSoft() {
        Collections.sort(sums);
        return sums.size() > 1 && sums.get(0) < 21 && sums.get(1) < 21;
    }

    public int getMaximumLessThan21() {
        Collections.sort(sums);
        int maxSum = 0;
        for (int sum : sums) {
            if (maxSum < sum && sum <= 21) {
                maxSum = sum;
            }
        }
        return maxSum;
    }
}
