//: com.thinking.generics/UseList.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;
import java.util.*;

public class UseList<W,T> {
    //wouldn't compile
//  void f(List<T> v) {}
  void f(List<W> v) {}
} ///:~
