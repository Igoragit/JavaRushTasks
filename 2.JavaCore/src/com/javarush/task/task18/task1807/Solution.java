package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());

        ArrayList<Character> characterArrayList = new ArrayList<>();

        while (fileInputStream.available()>0){
            characterArrayList.add((char) fileInputStream.read());
        }

        int count=0;
        for (Character ch: characterArrayList){
            int ascii = (int)ch;

            if(ascii==44){
                count++;
            }
        }


        System.out.println(count);
        fileInputStream.close();



    }
}
