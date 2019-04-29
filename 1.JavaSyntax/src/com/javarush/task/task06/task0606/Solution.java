package com.javarush.task.task06.task0606;

import java.io.*;
import java.util.ArrayList;

/*
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int intLine = Integer.parseInt(reader.readLine());

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        int num=0;

        while (true){

            num = intLine%10;
            intLine = (int)( intLine/10);
            integerArrayList.add(num);
            if(intLine==0) break;
        }

        for( Integer in: integerArrayList){

            if(in%2==0) even++;
            if(in%2!=0) odd++;
        }

        System.out.println("Even: " + even + " Odd: " + odd);

    }
}
