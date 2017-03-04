package com.hackerrank.code30days;

import com.lqw.common.WebPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Difference {
    private int[] elements;
    public int maximumDifference;
    public Difference(int[] a){
        elements = a;
    }

    public void computeDifference(){
        Arrays.sort(elements);
        this.maximumDifference = elements[elements.length-1] - elements[0];
    }
}

public class Scope {

    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(WebPath.getAbsolutePathWithClass("/com/hackerrank/code30days/input.txt").getPath()));
//        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}