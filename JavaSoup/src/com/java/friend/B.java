package com.java.friend;

public class B {
    private A.SomePrivateMethods key;

    public void receiveKey(A.SomePrivateMethods key) {
        this.key = key;
    }

    public void usageExample() {
        A anA = new A();

        //int foo = anA.privateInt; // doesn't work, not accessible

        anA.giveKeyTo(this);
        int fii = key.getSomethingPrivate();
        System.out.println(fii);
    }

    public static void main(String[] argv){
        A.SomePrivateMethods a = null;
        Class claz = A.SomePrivateMethods.class;
//        new A.SomePrivateMethods();
    }
}