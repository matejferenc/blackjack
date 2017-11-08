package com.mf.blackjack;

import com.mf.evolution.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Blackjack {

    public static void main(String args[]) {
        BlackjackPopulationInitializer populationInitializer = new BlackjackPopulationInitializer(100, new BlackjackDnaBuilder());
        PopulationSelector populationSelector = new PopulationSelector(0.3);
        BlackjackDnaBuilder dnaBuilder = new BlackjackDnaBuilder();
        BlackjackCrossoverBuilder blackjackCrossoverBuilder = new BlackjackCrossoverBuilder(dnaBuilder);
        Mutator mutator = new Mutator(0.1, 10, dnaBuilder);
        BlackjackConditionsEvaluator conditionsEvaluator = new BlackjackConditionsEvaluator();
        EvolutionAlgorithm evolutionAlgorithm = new EvolutionAlgorithm(populationSelector, blackjackCrossoverBuilder,
                mutator, populationInitializer, conditionsEvaluator);
        evolutionAlgorithm.run();
        Population population = evolutionAlgorithm.getPopulation();
        List<Individual> result = new ArrayList<>(population.getIndividuals());
        Collections.sort(result);
        System.out.println(result.get(0).getDna().toString());
    }
}
