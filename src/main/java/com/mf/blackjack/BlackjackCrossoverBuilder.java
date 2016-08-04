package com.mf.blackjack;

import com.mf.evolution.CrossoverBuilder;
import com.mf.evolution.Individual;

public class BlackjackCrossoverBuilder implements CrossoverBuilder {

    private BlackjackDnaBuilder builder;

    public BlackjackCrossoverBuilder(BlackjackDnaBuilder builder) {
        this.builder = builder;
    }
    
    @Override
    public Individual crossover(Individual mother, Individual father) {
        return new Individual(builder.crossover(mother.getDna(), father.getDna()));
    }
}
