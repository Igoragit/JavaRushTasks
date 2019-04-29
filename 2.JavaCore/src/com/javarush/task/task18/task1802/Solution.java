package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file);

        byte min=(byte) inputStream.read();
        byte input=0;

        while (inputStream.available()>0){

            input = (byte) inputStream.read();
            if(min>input) min=input;

        }

        System.out.println(min);
        inputStream.close();
    }
}
