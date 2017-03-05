//: com.thinking.generics/ReturnGenericType.java
package com.thinking.generics;

class ReturnGenericType<T extends HasF> {
    private T obj;

    public ReturnGenericType(T x) {
        obj = x;
    }

    public T get() {
        return obj;
    }
} ///:~
