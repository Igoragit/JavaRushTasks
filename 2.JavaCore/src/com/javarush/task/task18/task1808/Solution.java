package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        ArrayList<Byte> byteArrayList = new ArrayList<>();

            FileInputStream fileStreamReader = new FileInputStream(file1);
            int count=0;
            while (fileStreamReader.available()>0){

                byteArrayList.add( (byte)fileStreamReader.read());
                count++;
            }

        FileOutputStream fileOutputStream1 = new FileOutputStream(file2);
        FileOutputStream fileOutputStream2 = new FileOutputStream(file3);

        int sizeOfFirs=0;
        int sizeOfSecond=0;
        if(count%2!=0){
            sizeOfFirs = ((int)count/2)+1;
            sizeOfSecond = ((int)count/2);
        }
        else{
            sizeOfFirs=count/2;
            sizeOfSecond = ((int)count/2);
        }

        byte[] bytes = new byte[sizeOfFirs];
        byte[] bytes2 = new byte[sizeOfSecond];



        for (int x=0; x < sizeOfFirs; x++){

            bytes[x]=byteArrayList.get(x);
        }

        for (int x=0; x < sizeOfSecond; x++){

            bytes2[x]=byteArrayList.get(sizeOfFirs+x);
        }


        fileOutputStream1.write(bytes);
        fileOutputStream2.write(bytes2);

        fileStreamReader.close();
        fileOutputStream1.close();
        fileOutputStream2.close();


    }
}
