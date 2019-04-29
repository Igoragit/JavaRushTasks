package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();

        ArrayList<String> stringArrayList = new ArrayList<>();

        BufferedReader readFile = new BufferedReader(new FileReader(fileName));

        while (readFile.ready()){
            stringArrayList.add(readFile.readLine());
        }
        readFile.close();


        for (String in: stringArrayList){

            String[] spl = in.split(" ");

            int count = 0;

            for(String inio: spl){

                if(words.contains(inio));

            }

            if(count==2) System.out.println(in);

        }

    }
}
