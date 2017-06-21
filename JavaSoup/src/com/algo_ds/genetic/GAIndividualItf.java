package com.algo_ds.genetic;

/**
 * Created by Qingwei on 2017/5/15.
 */
public interface GAIndividualItf {

    double uniformRate = 0.5; //交叉概率
    double mutationRate = 0.015; //突变概率

    /**
     * 进行两个个体的交叉。 交叉的概率为uniformRate
     *
     * @param individual
     * @return
     */
    public GAIndividualItf crossover(GAIndividualItf individual);

    /**
     * 突变个体。 突变的概率为 mutationRate
     */
    public void mutate();


    /**
     * get the fitness
     *
     * @return
     */
    public double getFitness();

}
