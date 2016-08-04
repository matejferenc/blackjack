package com.mf.evolution;

import java.util.Random;

public class Mutator {

    private final double probability;

    private final int count;

    private final DnaBuilder dnaBuilder;

    public Mutator(double probability, int count, DnaBuilder dnaBuilder) {
        this.probability = probability;
        this.count = count;
        this.dnaBuilder = dnaBuilder;
    }

    public Individual mutate(Individual individual) {
        Dna dna = individual.getDna();
        for (int i = 0; i < count; i++) {
            Random random = new Random();
            if (random.nextDouble() < probability) {
                dna = dnaBuilder.mutate(dna);
                return new Individual(dna);
            }
        }
        return individual;
    }
    
}
