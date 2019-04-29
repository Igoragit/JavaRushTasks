package com.javarush.task.task36.task3602;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {

        Class<?>[] classes = Collections.class.getDeclaredClasses();

        for (Class<?> in : classes) {

            boolean modif = Modifier.isStatic(in.getModifiers()) && Modifier.isPrivate(in.getModifiers());

            if (modif) {
                try {

                    Constructor constructor = in.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List list = (List) constructor.newInstance();
                    list.get(0);
                } catch (IndexOutOfBoundsException e) {
                   // System.out.println(in.getName());
                    return in;
                    // e.printStackTrace();
                } catch (Exception e) {
                   // e.printStackTrace();

                }

            }
        }

        return null;
    }
}