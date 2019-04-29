package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
       BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        TreeSet<String> treeSet = new TreeSet<>();

        while (reader.ready()){

            String str = reader.readLine();
           // String str = "asdkjfbaskljdbfklj1142353453 . ;''[;/.";
            str.toLowerCase();
            str = str.replaceAll("[^a-z]", "");

            for (char in: str.toCharArray()){
                    treeSet.add(String.valueOf(in));
            }
        }

        StringBuilder stringBuilder = new StringBuilder();

        short i =1;
        for (String in: treeSet){
            if(i==6) break;
            stringBuilder.append(in);
            i++;
        }

        System.out.println(stringBuilder.toString());

    }
}
