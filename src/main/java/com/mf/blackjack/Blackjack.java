package com.mf.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mf.evolution.EvolutionAlgorithm;
import com.mf.evolution.Individual;
import com.mf.evolution.Mutator;
import com.mf.evolution.Population;
import com.mf.evolution.PopulationSelector;

public class Blackjack {

    public static void main(String args[]) {
        BlackjackPopulationInitializer populationInitializer = new BlackjackPopulationInitializer(1000, new BlackjackDnaBuilder());
        PopulationSelector populationSelector = new PopulationSelector(0.2);
        BlackjackDnaBuilder dnaBuilder = new BlackjackDnaBuilder();
        BlackjackCrossoverBuilder blackjackCrossoverBuilder = new BlackjackCrossoverBuilder(dnaBuilder);
        Mutator mutator = new Mutator(1, 1, dnaBuilder);
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
