package com.java.private_func_overload;

/**
 * Created by Qingwei on 16/6/16.
 */
public class Father {

    public void publicfunc(String a){
        privatefunc();
    }

    private void privatefunc(){
        System.out.println("hello father.");
    }
}
