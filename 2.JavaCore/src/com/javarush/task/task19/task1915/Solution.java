package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file = reader.readLine();
        reader.close();

        PrintStream def = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream New = new PrintStream(outputStream);
        System.setOut(New);

        testString.printSomething();

        byte[] b =outputStream.toByteArray();
        System.setOut(def);

        FileOutputStream outputStream1 = new FileOutputStream(file);
        outputStream1.write(b);
        System.out.println(outputStream.toString());
        outputStream1.close();

    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

