package com.mf.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mf.evolution.ConditionsEvaluator;
import com.mf.evolution.Individual;
import com.mf.evolution.Population;

public class BlackjackConditionsEvaluator implements ConditionsEvaluator {
    
    private final int GAMES_COUNT = 1000000;
    private final double MIN_BET = 1;
    private static int round = 0;

    @Override
    public boolean satisfied(Population population) {
        round++;
        System.out.println("ROUND " + round);
        for (Individual individual : population.getIndividuals()) {
            double money = 0;
            for (int game = 0; game < GAMES_COUNT; game++) {
                List<BlackjackGame.GameResult> results = new BlackjackGame((BlackjackDna) individual.getDna()).play();
                for (BlackjackGame.GameResult result : results) {
                    switch (result) {
                        case BLACKJACK:
                            money += MIN_BET * 1.5;
                            break;
                        case DOUBLE_LOSE:
                            money -= MIN_BET * 2;
                            break;
                        case DOUBLE_WIN:
                            money += MIN_BET * 2;
                            break;
                        case DRAW:
                            break;
                        case LOSE:
                            money -= MIN_BET;
                            break;
                        case SURRENDER:
                            money -= MIN_BET * 0.5;
                            break;
                        case WIN:
                            money += MIN_BET;
                            break;
                    }
                }
            }
            individual.setFitness(money);
            System.out.printf("%.0f, ", money);
        }
        System.out.println();
        List<Individual> individuals = new ArrayList<>(population.getIndividuals());
        Collections.sort(individuals);
        Individual best = individuals.get(individuals.size() - 1);
        System.out.println(best.getDna().toString());
        return best.getFitness() > 0;
    }
}
