package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код

        ArrayList<String>  stringArrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int x=0;
        while (x!=10){
            stringArrayList.add(reader.readLine());
            x++;
        }
        reader.close();


        int minIn = minStrIndex(stringArrayList);
        int maxIn = maxStrIndex(stringArrayList);

        if(minIn<maxIn) System.out.println(stringArrayList.get(minIn));
        if(minIn>maxIn) System.out.println(stringArrayList.get(maxIn));



    }

    public static   int minStrIndex(ArrayList<String> stringArrayList){

        int min=stringArrayList.get(0).length();
        int indexMin=0;
        for(int x=1; x<stringArrayList.size(); x++){

            if(stringArrayList.get(x).length()<min){
                min=stringArrayList.get(x).length();
                indexMin = x;
            }
        }
        return indexMin;
    }

    public static int maxStrIndex(ArrayList<String> stringArrayList){

        int max=stringArrayList.get(0).length();
        int indexMax=0;
        for(int x=1; x<stringArrayList.size(); x++){

            if(stringArrayList.get(x).length()>max){
                max=stringArrayList.get(x).length();
                indexMax = x;
            }
        }

        return indexMax;
    }
}
