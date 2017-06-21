package com.algo_ds.genetic;


/**
 * Individual – 个体，包含一个基因序列，对应于实际问题中的一个可选方案。
 * <p>
 * 关键在于定位到基因是什么，才可以进行交叉与突变的操作。
 * 基因就是决定适应性即效果的关键，如对于人的适应性是基因本身，
 * 在 traveling salesman problem 中基因是顺序。
 * <p>
 * Created by Qingwei on 2017/5/16.
 */
public abstract class GAIndividual<T> implements GAIndividualItf {

    public static int gene_length;
    //instance var
    protected T[] genes;      //基因序列
    protected double fitness = -1;// 个体的适应值

    //setters and getters.
    public T getGenes(int idx) {
        return genes[idx];
    }

    public void setGenes(int idx, T gene) {
        this.genes[idx] = gene;
        fitness = -1;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < genes.length; i++) {
            geneString += genes[i];
        }
        return geneString;
    }
}
