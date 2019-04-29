package com.javarush.task.task18.task1801;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
Максимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file);

        byte max=0;
        byte input=0;
        boolean ch=true;
        while (inputStream.available()>0){

            if(ch){
                max = (byte) inputStream.read();
                ch=false;
                continue;
            }


            input = (byte) inputStream.read();
            if(max<input) max=input;

        }
        System.out.println(max);
        inputStream.close();

    }

}
