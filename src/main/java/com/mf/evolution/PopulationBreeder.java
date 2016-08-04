package com.mf.evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopulationBreeder {

    private final int populationSize;

    private final CrossoverBuilder crossoverBuilder;
    private final Mutator mutator;

    public PopulationBreeder(int populationSize, CrossoverBuilder crossoverBuilder, Mutator mutator) {
        this.populationSize = populationSize;
        this.crossoverBuilder = crossoverBuilder;
        this.mutator = mutator;
    }

    public Population breed(Population population) {
        Population children = crossover(population);
        Population mutated = mutate(children);
        population.getIndividuals().addAll(mutated.getIndividuals());
        return population;
    }

    public Population mutate(Population population) {
        List<Individual> mutated = new ArrayList<>();
        population.getIndividuals().forEach(individual -> mutated.add(mutator.mutate(individual)));
        return new Population(mutated);
    }

    private Population crossover(Population population) {
        List<Individual> children = new ArrayList<>();
        for (int i = 0; i < populationSize - population.getIndividuals().size(); i++) {
            Individual mother = random(population);
            Individual father = random(population);
            Individual child = crossoverBuilder.crossover(mother, father);
            children.add(child);
        }
        return new Population(children);
    }
    
    private Individual random(Population population) {
        Random rand = new Random();
        Individual[] setArray = population.getIndividuals().toArray(new Individual[] {});
        return setArray[rand.nextInt(population.getIndividuals().size())];
    }
}
