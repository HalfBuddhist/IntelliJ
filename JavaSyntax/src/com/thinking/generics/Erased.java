//: com.thinking.generics/Erased.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;

public class Erased<T> {
  private final int SIZE = 100;
  public static void f(Object arg) {
      /* commented by lqw.
    if(arg instanceof T) {}          // Error
    T var = new T();                 // Error
    T[] array = new T[SIZE];         // Error
    T[] array = (T)new Object[SIZE]; // Unchecked warning
    */
  }
} ///:~
