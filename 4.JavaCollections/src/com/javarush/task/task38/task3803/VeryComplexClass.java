package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.Set;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object o = Integer.parseInt("33");
        VeryComplexClass complexClass = (VeryComplexClass)o;
    }

    public void methodThrowsNullPointerException() {
        Set<String> strings = null;
        strings.iterator().next();
    }

    public static void main(String[] args) {

    }
}
