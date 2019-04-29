package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

       // BufferedReader file1Reader = new BufferedReader(new FileReader(args[0]));
        //BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]));

        BufferedReader file1Reader = new BufferedReader(new FileReader("G:/file.txt"));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter("G:/file12.txt"));


        while ((file1Reader.ready())){

            String[] splittedLine = file1Reader.readLine().split(" ");

            for(String in: splittedLine){

                    if(!in.matches("^\\D*$")){
                        fileWriter.write(in + " ");
                    }

            }

        }

        file1Reader.close();
        fileWriter.close();
    }
}
