package com.mf.blackjack;

import java.util.ArrayList;
import java.util.List;

import com.mf.evolution.Dna;
import com.mf.evolution.Individual;
import com.mf.evolution.Population;
import com.mf.evolution.PopulationInitializer;

public class BlackjackPopulationInitializer implements PopulationInitializer {

    private final int size;
    private final BlackjackDnaBuilder blackjackDnaBuilder;

    public BlackjackPopulationInitializer(int size, BlackjackDnaBuilder blackjackDnaBuilder) {
        this.size = size;
        this.blackjackDnaBuilder = blackjackDnaBuilder;
    }
    
    @Override
    public Population initialize() {
        List<Individual> individuals = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            //Dna dna = blackjackDnaBuilder.random();
            Dna dna = BlackjackDna.fromFile("src/main/resources/best.txt");
            Individual individual = new Individual(dna);
            individuals.add(individual);
        }
        return new Population(individuals);
    }

    @Override
    public int getSize() {
        return size;
    }

}
