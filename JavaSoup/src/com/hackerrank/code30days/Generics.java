package com.hackerrank.code30days;

import java.lang.reflect.Method;

public class Generics {

    public static <T> void printArray(T[] array){
        for (T ele: array){
            System.out.println(ele);
        }
    }

    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};

        printArray(intArray);
        printArray(stringArray);

        if (Generics.class.getDeclaredMethods().length > 2) {
            System.out.println("You should only have 1 method named printArray.");
        }
    }
}
