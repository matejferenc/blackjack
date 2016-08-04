package com.mf.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopulationSelector {
    
    private final double percentage;
    
    public PopulationSelector(double percentage) {
        this.percentage = percentage;
    }

    public Population selectBest(Population population) {
        int bestCount = (int) Math.round(population.getIndividuals().size() * percentage);
        ArrayList<Individual> individuals = new ArrayList<>(population.getIndividuals());
        Collections.sort(individuals);
        List<Individual> bestIndividuals = individuals.subList(individuals.size() - bestCount, individuals.size());
        return new Population(bestIndividuals);
    }

}
