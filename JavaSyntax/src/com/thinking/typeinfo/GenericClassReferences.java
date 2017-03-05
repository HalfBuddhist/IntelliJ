//: com.thinking.typeinfo/GenericClassReferences.java
package com.thinking.typeinfo;

import static net.mindview.util.Print.*;

public class GenericClassReferences {
    public static void main(String[] args) {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // Same thing
        genericIntClass = Integer.TYPE;
//        print(Integer.TYPE == int.class);
//        print(Integer.class.equals(Integer.TYPE));
        intClass = double.class;
        // genericIntClass = double.class; // Illegal
    }
} ///:~
