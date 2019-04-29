package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> integerArrayList = new ArrayList<>();


        while (true){

            int lineInt = Integer.parseInt(reader.readLine());

            if(lineInt!=-1){
            integerArrayList.add(lineInt);
            }
            else break;
        }

        double result=0;

        for(Integer in: integerArrayList){

            result+=in;

        }

        result=result/integerArrayList.size();
        System.out.println(result);


    }
}

