package com.acmerblog.tsp_ga;

import com.algo_ds.genetic.GAIndividual;
import com.algo_ds.genetic.GAIndividualItf;
import com.lqw.common.ArraysUtils;

import java.util.*;

/**
 * 每个Tour就是种群的一个  个体
 */
public class Tour extends GAIndividual<City> {

    public static final ArrayList<City> total_cities = new ArrayList<City>();
    //instance var.
    protected double distance = -1;

    // Constructs a blank tour
    public Tour(boolean if_fill_gene) {
        genes = new City[gene_length];
        if (if_fill_gene) {
            Arrays.setAll(genes, i -> total_cities.get(i));
            //shuffle
            ArraysUtils.shuffle(genes);
        }
    }


    @Override
    public void setGenes(int idx, City gene) {
        super.setGenes(idx, gene);
        distance = -1;
    }

    /**
     * 距离小的 适应值较大
     *
     * @return
     */
    @Override
    public double getFitness() {
        if (fitness < 0) {
            fitness = 1 / getDistance();
        }
        return fitness;
    }

    // 获取当前路径的总距离
    public double getDistance() {
        if (distance < 0) {
            double tourDistance = 0;
            for (int i = 0; i < gene_length; i++) {
                City fromCity = getGenes(i);
                City destinationCity;
                // Check we're not on our tour's last city, if we are set our 
                // tour's final destination city to our starting city
                if (i + 1 < gene_length) {
                    destinationCity = getGenes(i + 1);
                } else {
                    destinationCity = getGenes(0);
                }
                // Get the distance between the two cities
                tourDistance += fromCity.distanceTo(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }

    /**
     * 对parent1和parent2进行交叉操作，生成新的 tour 路径
     *
     * @param individual
     * @return
     */
    @Override
    public GAIndividualItf crossover(GAIndividualItf individual) {
        // Create new child tour
        Tour child = new Tour(false);

        // startPos endPos之间的序列，会被遗传到下一代。 (如果startPos<endPos,就是取反)
        int startPos = (int) (Math.random() * gene_length);
        int endPos = (int) (Math.random() * gene_length);
        if (startPos > endPos) {
            int t = startPos;
            startPos = endPos;
            endPos = t;
        }
        // Loop and add the sub tour from parent1 to our child
        HashMap<Object, Boolean> index = new HashMap<>();
        for (int i = startPos; i <= endPos; i++) {
            child.setGenes(i, getGenes(i));
            index.put(getGenes(i), true);
        }

        // 由于child已经继承了parent1的部分city.
        // 下面就是找 parent2中还被child继承的那些city 要保证city的唯一性
        Tour parent2 = (Tour) individual;
        int idx = -1;
        for (int i = 0; i < gene_length; i++) {
            // If child doesn't have the city add it
            if (!index.containsKey(parent2.getGenes(i))) {
                while (child.getGenes(++idx) != null) ;
                child.setGenes(idx, parent2.getGenes(i));
            }
        }
        return child;
    }

    private boolean containsCity(City city) {
        return Arrays.stream(genes).anyMatch(e -> {
            if (e != null && e.equals(city))
                return true;
            return false;
        });
    }

    /**
     * 突变操作。随机交换
     */
    @Override
    public void mutate() {
        // Loop through tour cities
        for (int tourPos1 = 0; tourPos1 < gene_length; tourPos1++) {
            // Apply mutation rate
            if (Math.random() < mutationRate) {
                // Get a second random position in the tour
                int tourPos2 = (int) (gene_length * Math.random());
                // Get the cities at target position in tour
                City city1 = getGenes(tourPos1);
                City city2 = getGenes(tourPos2);

                // Swap them around
                setGenes(tourPos2, city1);
                setGenes(tourPos1, city2);
            }
        }
    }

    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < gene_length; i++) {
            geneString += getGenes(i) + "|";
        }
        return geneString;
    }
}