package com.mf.evolution;

public interface DnaBuilder {

    Dna random();

    Dna mutate(Dna dna);
    
    Dna crossover(Dna mother, Dna father);
    
}
