//: com.thinking.typeinfo/toys/ToyTest.java
// Testing class Class.
package com.thinking.typeinfo.toys;

import static net.mindview.util.Print.*;

interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
    // Comment out the following default constructor
    // to see NoSuchMethodError from (*1*)
    Toy() {
    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}

public class ToyTest {
    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        c = int.class;
        try {
            c = Class.forName("com.thinking.typeinfo.toys.FancyToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces())
            printInfo(face);
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // Requires default constructor:
            obj = up.newInstance();
        } catch (InstantiationException e) {
            print("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            print("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
} /* Output:
Class name: com.thinking.typeinfo.toys.FancyToy is interface? [false]
Simple name: FancyToy
Canonical name : com.thinking.typeinfo.toys.FancyToy
Class name: com.thinking.typeinfo.toys.HasBatteries is interface? [true]
Simple name: HasBatteries
Canonical name : com.thinking.typeinfo.toys.HasBatteries
Class name: com.thinking.typeinfo.toys.Waterproof is interface? [true]
Simple name: Waterproof
Canonical name : com.thinking.typeinfo.toys.Waterproof
Class name: com.thinking.typeinfo.toys.Shoots is interface? [true]
Simple name: Shoots
Canonical name : com.thinking.typeinfo.toys.Shoots
Class name: com.thinking.typeinfo.toys.Toy is interface? [false]
Simple name: Toy
Canonical name : com.thinking.typeinfo.toys.Toy
*///:~
