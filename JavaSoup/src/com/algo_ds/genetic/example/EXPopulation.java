package com.algo_ds.genetic.example;

import com.algo_ds.genetic.GAIndividualItf;
import com.algo_ds.genetic.GAPopulation;

import java.util.Arrays;

/**
 * Created by Qingwei on 2017/5/16.
 */
public class EXPopulation extends GAPopulation {

    /**
     * 创建一个种群
     *
     * @param size
     * @param ifInitial if initialize the population.
     */
    public EXPopulation(int size, boolean ifInitial) {
        individuals = createIndividuals(size, ifInitial, i -> {
            return new EXIndividual();
        });
    }
}
