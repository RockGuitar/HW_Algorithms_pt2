package ru.skypro;

import java.util.Arrays;

public class Main {

    public static void main ( String[] args ) {
        StringListRealisation myArray = new StringListRealisation();


        myArray.add("Item1");
        myArray.add("Item2");
        System.out.println(myArray.toString());
        myArray.set(3, "Item4");
        myArray.add("Item3");
        System.out.println(myArray.toString());
        myArray.add(2, "go");
        System.out.println(myArray.toString());
        myArray.remove(2);
        System.out.println(myArray.toString());
        myArray.remove("Item1");
        System.out.println(myArray.toString());
        System.out.println(Arrays.toString(myArray.toArray()));

//        myArray.add(3, "Item5");
//        myArray.add(3, "Item6");
//        System.out.println(myArray.toString());
//        myArray.remove(2);
//        System.out.println(myArray.toString());
//        myArray.remove("Item6");
//        myArray.remove("Item4");
//        System.out.println(myArray.toString());
//
//        System.out.println(myArray.size());
//        System.out.println("myArray.isEmpty() = " + myArray.isEmpty());
//        System.out.println("myArray.get(1) = " + myArray.get(1));
//        System.out.println("myArray.indexOf(\"Item5\") = " + myArray.indexOf("Item5"));
//        System.out.println("myArray.contains(\"Item2\") = " + myArray.contains("Item2"));
//        System.out.println("myArray.contains(\"anyitem\") = " + myArray.contains("anyitem"));
//        StringListRealisation otherArray = new StringListRealisation();
//        otherArray.arrayStr = Arrays.copyOf(myArray.arrayStr, StringListRealisation.numberOfElements);
//        System.out.println(otherArray.toString());
//        System.out.println("myArray.equals(otherArray) = " + myArray.equals(otherArray));
//        StringListRealisation thirdArray = new StringListRealisation();
//        thirdArray.arrayStr = myArray.toArray();
//        System.out.println(thirdArray.equals(myArray));


    }
}
