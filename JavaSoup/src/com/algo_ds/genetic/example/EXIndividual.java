package com.algo_ds.genetic.example;

import com.algo_ds.genetic.GAIndividual;
import com.algo_ds.genetic.GAIndividualItf;

/**
 * Individual – 个体，包含一个基因序列
 */
public class EXIndividual extends GAIndividual<Byte> {

    static Byte[] perfact_gene = null;

    /* Public methods */
    // 设置候选结果为一个 byte array
    public static void setSolution(Byte[] newSolution) {
        perfact_gene = newSolution;
    }

    // 就是把01 字符串转换为 01数组， 放在 solution中
    public static void setSolution(String newSolution) {
        perfact_gene = new Byte[gene_length];
        // Loop through each character of our string and save it in our byte
        for (int i = 0; i < newSolution.length(); i++) {
            String character = newSolution.substring(i, i + 1);
            if (character.contains("0") || character.contains("1")) {
                perfact_gene[i] = Byte.parseByte(character);
            } else {
                perfact_gene[i] = 0;
            }
        }
    }

    /**
     * 创建一个随机的 基因个体
     */
    public EXIndividual() {
        genes = new Byte[gene_length];
        for (int i = 0; i < gene_length; i++) {
            byte gene = (byte) Math.round(Math.random()); //random 0 and 1
            genes[i] = gene;
        }
    }

    /**
     * 通过和perfact_gene比较 ，计算个体的适应值
     *
     * @return
     */
    @Override
    public double getFitness() {
        if (fitness < 0) {
            fitness = 0;
            for (int i = 0; i < gene_length; i++) {
                if (genes[i].equals(perfact_gene[i]))
                    fitness += 1;
            }
        }
        return fitness;
    }

    @Override
    public GAIndividualItf crossover(GAIndividualItf individual) {
        EXIndividual new_indiv = new EXIndividual();
        // 随机的从 两个个体中选择
        for (int i = 0; i < gene_length; i++) {
            if (Math.random() <= uniformRate) {
                new_indiv.setGenes(i, genes[i]);
            } else {
                new_indiv.setGenes(i, (Byte) ((GAIndividual) individual).getGenes(i));
            }
        }
        return new_indiv;
    }

    @Override
    public void mutate() {
        for (int i = 0; i < gene_length; i++) {
            if (Math.random() <= mutationRate) {
                // 生成随机的 0 或 1
                byte gene = (byte) Math.round(Math.random());
                setGenes(i, gene);
            }
        }
    }
}