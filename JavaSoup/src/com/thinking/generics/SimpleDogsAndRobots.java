//: com.thinking.generics/SimpleDogsAndRobots.java
// Removing the generic; code still works.
package com.thinking.generics;

import com.thinking.generics.Performs;

class CommunicateSimply {
    static void perform(Performs performer) {
        performer.speak();
        performer.sit();
    }
}

public class SimpleDogsAndRobots {
    public static void main(String[] args) {
        CommunicateSimply.perform(new PerformingDog());
        CommunicateSimply.perform(new Robot());
    }
} /* Output:
Woof!
Sitting
Click!
Clank!
*///:~
