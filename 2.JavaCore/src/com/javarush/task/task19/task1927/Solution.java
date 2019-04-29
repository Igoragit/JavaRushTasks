package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream def = System.out;

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream  myStream = new PrintStream(stream);
        System.setOut(myStream);
        testString.printSomething();


        String str = stream.toString();
        System.setOut(def);

        String[] sp = str.split("\n");

        int count =0;
        for (int x =0; x <sp.length; x++){

            System.out.println(sp[x]);
            count++;
            if(count==2){
                System.out.println("JavaRush - курсы Java онлайн");
                count=0;
            }

        }

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
