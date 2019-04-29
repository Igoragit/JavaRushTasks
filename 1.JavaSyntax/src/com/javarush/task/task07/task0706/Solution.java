package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        //напишите тут ваш код


        int[] ints = new int[15];

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        for(int x =0; x<15; x++){

            ints[x]=Integer.parseInt(reader.readLine());
        }

        int chet=0;
        int nechet=0;

        for(int q= 0; q<= ints.length;q=q+2 ){
            nechet+=ints[q];
        }

        for(int q= 1; q<= ints.length-1;q=q+2 ){
            chet+=ints[q];
        }

        if(chet<nechet) System.out.println("В домах с четными номерами проживает больше жителей.");
        if(chet>nechet) System.out.println("В домах с нечетными номерами проживает больше жителей.");
    }
}
