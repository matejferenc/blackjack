package com.mf.evolution;

public class Individual implements Comparable<Individual> {

    private double fitness;
    private final Dna dna;

    public Individual(Dna dna) {
        this.dna = dna;
    }

    @Override
    public int compareTo(Individual other) {
        return (int) Math.round(this.fitness - other.fitness);
    }

    public Dna getDna() {
        return dna;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }
}
