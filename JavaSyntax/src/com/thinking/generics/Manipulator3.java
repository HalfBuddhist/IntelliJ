//: com.thinking.generics/Manipulator3.java
package com.thinking.generics;
import com.thinking.generics.HasF;


class Manipulator3 {
  private HasF obj;
  public Manipulator3(HasF x) { obj = x; }
  public void manipulate() { obj.f(); }
} ///:~
