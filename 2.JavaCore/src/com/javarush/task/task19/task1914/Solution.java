package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream defoult = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOne = new PrintStream(outputStream);
        System.setOut(newOne);
        testString.printSomething();

        String str = outputStream.toString();
        System.setOut(defoult);

        String[] spl = str.split(" ");
        int n1 = Integer.parseInt(spl[0]);
        int n2 = Integer.parseInt(spl[2]);

        String znak = spl[1];

        int res=0;

        if(Objects.equals(znak, "+"))res = n1 + n2;
        if(Objects.equals(znak, "-"))res = n1 - n2;
        if(Objects.equals(znak, "*"))res = n1 * n2;

        System.out.println(n1 + " " + znak + " " + n2 + " = " +res );



    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

