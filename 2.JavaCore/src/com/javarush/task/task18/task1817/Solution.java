package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {


       FileInputStream inputStream = new FileInputStream(args[0]);
       FileInputStream inputStream2 = new FileInputStream(args[0]);
        //FileInputStream inputStream = new FileInputStream("J:/file13.txt");
        //FileInputStream inputStream2 = new FileInputStream("J:/file13.txt");

        double countOfSpace=0;

        while (inputStream.available()>0){

            int numb = inputStream.read();
            if(numb == ' ') countOfSpace++;

        }

        double countOfSim = inputStream2.available();

        inputStream.close();
        inputStream2.close();

        double d= (countOfSpace/countOfSim)*100;
        d = Math.round(d*100.0)/100.0;
        System.out.println(d);


    }
}
