package com.algo_ds.genetic;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * 遗传算法
 * GAPopulation 一个种群，管理所有的个体
 * <p>
 * Created by Qingwei on 2017/5/16.
 */
public abstract class GAPopulation implements GAPopulationItf {
    protected GAIndividualItf[] individuals;

    /**
     * 创建一个种群
     *
     * @param size
     * @param ifInitial if initialize the population.
     */
    public GAIndividualItf[] createIndividuals(int size, boolean ifInitial, IntFunction<? extends GAIndividualItf> generator) {
        GAIndividualItf[] indivis = new GAIndividualItf[size];
        // 初始化种群
        if (ifInitial) {
            Arrays.setAll(indivis, generator);
        }
        return indivis;
    }

    @Override
    public void evolve() {
        // 存放新一代的种群
        GAIndividualItf[] new_individuals = new GAIndividualItf[size()];

        // 把最优秀的那个 放在第一个位置.
        int elitismOffset = 0;
        if (elitism) {
            new_individuals[0] = this.getFittest();
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the population size and create new individuals with
        for (int i = elitismOffset; i < size(); i++) {
            //随机选择两个 优秀的个体
            GAIndividualItf indiv1 = tournamentSelection();
            GAIndividualItf indiv2 = tournamentSelection();
            //进行交叉
            GAIndividualItf newIndiv = indiv1.crossover(indiv2);
            new_individuals[i] = newIndiv;
        }

        // Mutate population  突变
        for (int i = elitismOffset; i < size(); i++) {
            new_individuals[i].mutate();
        }

        this.individuals = new_individuals;
    }

    @Override
    public GAIndividualItf tournamentSelection() {
        // Create a tournament population
        GAIndividualItf[] t = createIndividuals(tournamentSize, false, null);
        //随机选择 tournamentSize 个放入 tournamentPop 中
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * size());
            t[i] = individuals[randomId];
        }
        // 找到淘汰数组中最优秀的
        GAIndividualItf fittest = Arrays.stream(t).max((i, j) -> {
            double cha = i.getFitness() - j.getFitness();
            if (cha > 0) return 1;
            else if (cha < 0) return -1;
            else return 0;
        }).get();

        return fittest;
    }

    @Override
    public GAIndividualItf getFittest() {
        return Arrays.stream(individuals).max((i, j) -> {
            double cha = i.getFitness() - j.getFitness();
            if (cha > 0) return 1;
            else if (cha < 0) return -1;
            else return 0;
        }).get();
    }

    /**
     * Get population size
     */
    @Override
    public int size() {
        return individuals.length;
    }
}
