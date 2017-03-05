//: com.thinking.typeinfo/pets/ForNameCreator.java
package com.thinking.typeinfo.pets;
import java.util.*;

public class ForNameCreator extends PetCreator {
  private static List<Class<? extends Pet>> types =
    new ArrayList<Class<? extends Pet>>();
  // Types that you want to be randomly created:
  private static String[] typeNames = {
    "com.thinking.typeinfo.pets.Mutt",
    "com.thinking.typeinfo.pets.Pug",
    "com.thinking.typeinfo.pets.EgyptianMau",
    "com.thinking.typeinfo.pets.Manx",
    "com.thinking.typeinfo.pets.Cymric",
    "com.thinking.typeinfo.pets.Rat",
    "com.thinking.typeinfo.pets.Mouse",
    "com.thinking.typeinfo.pets.Hamster"
  };	
  @SuppressWarnings("unchecked")
  private static void loader() {
    try {
      for(String name : typeNames)
        types.add(
          (Class<? extends Pet>)Class.forName(name));
    } catch(ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  static { loader(); }
  public List<Class<? extends Pet>> types() {return types;}
} ///:~
