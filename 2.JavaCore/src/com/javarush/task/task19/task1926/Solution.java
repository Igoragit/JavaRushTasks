package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader fileReader = new BufferedReader(new FileReader(reader.readLine()));

        reader.close();

        ArrayList<String> arrayList = new ArrayList<>();

        while (fileReader.ready()){
            arrayList.add(fileReader.readLine());
        }

        fileReader.close();

        ArrayList<String> inverted = new ArrayList<>();

        for (String in: arrayList){

            String string="";
            for (int x = in.length()-1; x>=0; x --){
                string += in.charAt(x);
            }
            inverted.add(string);
        }

        for (String In: inverted){

            System.out.println(In);

        }


    }
}
