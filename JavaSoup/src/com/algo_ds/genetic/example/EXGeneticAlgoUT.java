package com.algo_ds.genetic.example;

import com.algo_ds.genetic.GAPopulationItf;
import org.junit.Test;

/**
 * Created by Qingwei on 2017/5/16.
 */
public class EXGeneticAlgoUT {
    @Test
    public void test_genetic() {
        // 选择一个期望的基因序列。这个是由自己任意定
        EXIndividual.gene_length = 64;
        EXIndividual.setSolution("1111000000000000000000000000000000000000000000000000000000001111");

        // 初始化一个种群
        GAPopulationItf population = new EXPopulation(50, true);

        // 不段迭代，进行进化操作。 直到找到期望的基因序列
        int generationCount = 0;
        while (population.getFittest().getFitness() < EXIndividual.gene_length) {//最优的适应值，即为基因序列的长度
            generationCount++;
            System.out.println("Generation: " + generationCount +
                    " Fittest: " + population.getFittest().getFitness());
            population.evolve();
        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Final Fittest Genes:");
        System.out.println(population.getFittest());

    }
}
