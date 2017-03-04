//: com.thinking.generics/HijackedInterface.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;

class Cat implements Comparable<Cat>{
//class Cat extends ComparablePet implements Comparable<Cat>{
  // Error: Comparable cannot be inherited with
  // different arguments: <Cat> and <Pet>
  public int compareTo(Cat arg) { return 0; }
} ///:~
