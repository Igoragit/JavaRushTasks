package com.javarush.task.task19.task1913;

/* 
Выводим только цифры
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        PrintStream defoult = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newPrintstream = new PrintStream(outputStream);
        System.setOut(newPrintstream);

        testString.printSomething();

        String result = outputStream.toString();
        String[] spl = result.split("\\D");

        String out = "";
        for(String in: spl){
            if(!in.equals("")) out+=in;
        }

        System.setOut(defoult);
        System.out.println(out);


    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's 1 a 23 text 4 f5-6or7 tes8ting");
        }
    }
}
