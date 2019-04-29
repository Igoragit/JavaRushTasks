package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        reader.close();

        FileReader fileReader = new FileReader(file);
        String str="";
        while (fileReader.ready()){

            str+=  (char)fileReader.read();

        }

        fileReader.close();

       String[]  splitted =  str.split("\\W");
        int q=0;
        for(String in: splitted){

            if (in.equals("world")) q++;
        }

        System.out.println(q);

    }
}
