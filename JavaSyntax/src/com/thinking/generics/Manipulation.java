//: com.thinking.generics/Manipulation.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;

class Manipulator<T> {
    private T obj;

    public Manipulator(T x) {
        obj = x;
    }
    // Error: cannot find symbol: method f():
//  public void manipulate() { obj.f(); } //comment by lqw
}

public class Manipulation {
    public static void main(String[] args) {
        HasF hf = new HasF();
        Manipulator<HasF> manipulator =
                new Manipulator<HasF>(hf);
//    manipulator.manipulate();  //comment by lqw
    }
} ///:~
