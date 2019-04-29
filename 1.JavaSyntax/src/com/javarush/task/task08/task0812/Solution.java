package com.javarush.task.task08.task0812;

import java.io.*;
import java.util.ArrayList;


/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //напишите тут ваш код

        ArrayList<Integer> integerArrayList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for(int x=0; x<10; x++){
            integerArrayList.add(Integer.parseInt(reader.readLine()));
        }
        reader.close();

        System.out.println(getSec2(integerArrayList));
    }


    public  static int getSec2(ArrayList<Integer> ints){

        int lenghtCount=1;
        int lasSecLenght=1;

        for(int x=1; x<ints.size(); x++){

            if(ints.get(x-1)==ints.get(x))   lenghtCount++;
            else {

                if(lenghtCount>lasSecLenght)lasSecLenght=lenghtCount;
                lenghtCount=1;
            }
        }

        if(lasSecLenght>lenghtCount) return lasSecLenght;
        else return lenghtCount;
    }
}