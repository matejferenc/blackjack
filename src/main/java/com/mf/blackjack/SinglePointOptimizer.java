package com.mf.blackjack;

import com.mf.evolution.ConditionsEvaluator;
import com.mf.evolution.Mutator;
import com.mf.evolution.Population;
import com.mf.evolution.PopulationBreeder;
import com.mf.evolution.PopulationInitializer;

public class SinglePointOptimizer {

    private static ConditionsEvaluator conditionsEvaluator;

    private static PopulationInitializer populationInitializer;

    private static PopulationBreeder populationBreeder;

    private static Population population;
    
    public static void main(String args[]) {
        populationInitializer = new BlackjackPopulationInitializer(10, new BlackjackDnaBuilder());
        BlackjackDnaBuilder dnaBuilder = new BlackjackDnaBuilder();
        Mutator mutator = new Mutator(1, 1, dnaBuilder);
        populationBreeder = new PopulationBreeder(populationInitializer.getSize(), null, mutator);
        conditionsEvaluator = new BlackjackConditionsEvaluator();
        new SinglePointOptimizer().run();
    }

    public void run() {
        population = populationInitializer.initialize();
        while (!conditionsEvaluator.satisfied(population)) {
            population = populationBreeder.breed(population);
        }
    }
    
}
