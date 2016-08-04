package com.mf.evolution;

public class EvolutionAlgorithm {

    private Population population;

    private ConditionsEvaluator conditionsEvaluator;

    private PopulationSelector populationSelector;

    private PopulationBreeder populationBreeder;

    private PopulationInitializer populationInitializer;

    public EvolutionAlgorithm(PopulationSelector populationSelector, CrossoverBuilder crossoverBuilder,
                              Mutator mutator, PopulationInitializer populationInitializer,
                              ConditionsEvaluator conditionsEvaluator) {
        this.populationSelector = populationSelector;
        this.populationInitializer = populationInitializer;
        this.conditionsEvaluator = conditionsEvaluator;
        populationBreeder = new PopulationBreeder(populationInitializer.getSize(), crossoverBuilder, mutator);
    }

    public void run() {
        population = populationInitializer.initialize();
        while (!conditionsEvaluator.satisfied(population)) {
            population = populationSelector.selectBest(population);
            population = populationBreeder.breed(population);
        }
    }

    public Population getPopulation() {
        return population;
    }

}
