package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;
import java.util.ArrayList;


public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        reader.close();

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        FileReader fileReader = new FileReader(file1);
        while (fileReader.ready()){

            integerArrayList.add(fileReader.read());
        }

        fileReader.close();

        FileWriter fileWriter = new FileWriter(file2);

        for(int x=1; x<integerArrayList.size(); x=x+2){
            fileWriter.write(integerArrayList.get(x));
        }
        fileWriter.close();


    }
}
