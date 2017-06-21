package com.algo_ds.genetic;

/**
 * Population – 一个种群，管理所有的个体
 * Created by Qingwei on 2017/5/15.
 */
public interface GAPopulationItf {

    /* GA 算法的参数 */
    int tournamentSize = 5; //淘汰数组的大小
    boolean elitism = true; //精英主义， 每次进化是否保留精英


    /**
     * population evolving once.
     * 进化一个种群
     */
    public void evolve();


    /**
     * 随机选择一个较优秀的个体，用于进行交叉
     *
     * @return
     */
    public GAIndividualItf tournamentSelection();

    /**
     * Loop through individuals to find fittest
     *
     * @return
     */
    public GAIndividualItf getFittest();

    /**
     * Get population size
     */
    public int size();
}
