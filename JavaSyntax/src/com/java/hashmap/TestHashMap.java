package com.java.hashmap;

import java.util.HashMap;

/**
 * hashmap中， key的hash值是调用自object.hashcode方法，默认取对象地址作hash, 字符串则是取用字符串本身，数字则是数字本身。
 */

public class TestHashMap {

    public static void main(String[] args)
    {
        String a = new String("hello");
//        String b = new String("helloa");
        StringBuffer b = new StringBuffer("hello");
        String c = new String("hello");
        String d = a;

        String e = "hello";
        String f = "hello";

        System.out.println("new and constant");
        System.out.println(a == e );
        System.out.println(a.equals(e));
        System.out.println(a.hashCode());
        System.out.println(e.hashCode());
        System.out.println(System.identityHashCode(a)); //hashcode of the address value
        System.out.println(System.identityHashCode(e));

        System.out.println("constant and constant");
        System.out.println(e == f);
        System.out.println(e.equals(f));
        System.out.println(System.identityHashCode(e));
        System.out.println(System.identityHashCode(f));


        System.out.println("new and new");
        System.out.println(a == c);
        System.out.println(a.equals(c));
        System.out.println(a.hashCode());
        System.out.println(c.hashCode());
        System.out.println(System.identityHashCode(a));
        System.out.println(System.identityHashCode(c));

        System.out.println("hashcode and identityhashcode");
        System.out.println(d.hashCode());
        System.out.println(System.identityHashCode(d));

        System.out.println(b.hashCode());
        System.out.println(System.identityHashCode(b)); //which is the same

        System.out.println("//////");



        System.out.println("//////");

        System.out.println(a == d);
        System.out.println(a.equals(d));

        System.out.println("//////");

        HashMap<Object, Integer> hm = new HashMap<Object, Integer>();
        hm.put(a, 1);
        hm.put(b, 2);

        for (Object key : hm.keySet())
        {
            System.out.println(hm.get(key));
        }

        b.append(" helllo world");

        System.out.println("b changed");

        for (Object key : hm.keySet())
        {
            System.out.println(hm.get(key));
        }

        System.out.println((Object)a);
        System.out.println((Object)b);

        System.out.print("hello world!");
    }
}
