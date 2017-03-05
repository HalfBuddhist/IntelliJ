//: com.thinking.generics/NonCovariantGenerics.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;
import java.util.*;

public class NonCovariantGenerics {
  // Compile Error: incompatible types:
//  List<Fruit> flist = new ArrayList<Apple>();
} ///:~
