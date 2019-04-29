package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream inputStream = new FileInputStream(file1);

        ArrayList<Byte> byteArrayList = new ArrayList<>();

        while (inputStream.available()>0){
            byteArrayList.add((byte)inputStream.read());
        }

        FileOutputStream outputStream = new FileOutputStream(file2);

        for (int x = byteArrayList.size()-1; x>=0; x--){

            outputStream.write(byteArrayList.get(x));

        }
        inputStream.close();
        outputStream.close();
        //System.out.println(byteArrayList);


    }
}
