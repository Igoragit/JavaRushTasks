package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1="";
        String file2="";

        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
            reader.close();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));



        String file="";
        while (bufferedReader.ready()){
            file+=(char)bufferedReader.read();
        }
        bufferedReader.close();

       String newString = file.replace('.','!');

        bufferedWriter.write(newString);
        bufferedWriter.close();

    }catch (IOException e){

    }


}
}
