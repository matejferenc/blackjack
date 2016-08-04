package com.mf.evolution;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Population {

    private Set<Individual> individuals = new HashSet<>();

    public Population(List<Individual> individuals) {
        this.individuals = new HashSet<>(individuals);
    }

    public Set<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(Set<Individual> individuals) {
        this.individuals = individuals;
    }
}
