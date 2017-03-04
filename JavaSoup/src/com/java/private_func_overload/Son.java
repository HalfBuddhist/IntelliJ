package com.java.private_func_overload;

/**
 * Created by Qingwei on 16/6/16.
 */
public class Son extends Father {
    public void publicfunc(int a){
        System.out.println(a);
    }
    private void privatefunc(){
        System.out.println("hello son.");
    }

    public static void main(String[] argv){
//        Son son = new Son();
//        son.privatefunc();
//        son.publicfunc();
        System.out.println("heloworld");
    }
}
