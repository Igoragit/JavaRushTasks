package com.javarush.task.task07.task0718;

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

        if(minIn!=stringArrayList.size()) System.out.println(minIn);


    }

    public static   int minStrIndex(ArrayList<String> stringArrayList){

        int first=stringArrayList.get(0).length();
        int indexMin=0;
        for(int x=1; x<stringArrayList.size(); x++){

            if(stringArrayList.get(x).length()>first){
                first=stringArrayList.get(x).length();
                indexMin = x;
            }
            else break;
        }
        return indexMin+1;
    }

}
