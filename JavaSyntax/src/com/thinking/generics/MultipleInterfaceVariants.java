//: com.thinking.generics/MultipleInterfaceVariants.java
// {CompileTimeError} (Won't compile)
package com.thinking.generics;

interface Payable<T> {
}

class Employee implements Payable<Employee> {
}

//class Hourly extends Employee implements Payable<Hourly> {} ///:~
