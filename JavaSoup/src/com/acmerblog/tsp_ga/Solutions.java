package com.acmerblog.tsp_ga;

import com.algo_ds.genetic.GAIndividualItf;
import com.algo_ds.genetic.GAPopulation;

public class Solutions extends GAPopulation {

    // Construct a population
    public Solutions(int populationSize, boolean initialise) {
        individuals = createIndividuals(populationSize, initialise, i -> {
            return new Tour(true);
        });
    }
}