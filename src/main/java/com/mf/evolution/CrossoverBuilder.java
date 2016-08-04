package com.mf.evolution;

public interface CrossoverBuilder {

    Individual crossover(Individual mother, Individual father);
    
}
