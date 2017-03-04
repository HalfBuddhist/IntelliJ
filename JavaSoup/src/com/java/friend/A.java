package com.java.friend;

public class A {
    private int privateInt = 31415;

    public class SomePrivateMethods {
        public int getSomethingPrivate() {
            return privateInt;
        }

        private SomePrivateMethods() {
        } // no public constructor
    }

    public void giveKeyTo(B other) {
        other.receiveKey(new SomePrivateMethods());
    }
}
