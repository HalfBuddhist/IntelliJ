package com.java.private_func_overload;

/**
 * Created by Qingwei on 16/6/16.
 */
public class TestClass {
    public static void main(String[] args) {
        Father father = new Father();
//        father.privatefunc();
        Son son = new Son();
        son.publicfunc("123");
        son.publicfunc(123);
        System.out.println("Hello world!");
    }
}
