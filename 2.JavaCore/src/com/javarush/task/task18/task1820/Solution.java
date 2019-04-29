package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;


public class Solution {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        reader.close();

        FileInputStream inputStream1 = new FileInputStream(file1);

        byte[] buffer1 = new byte[inputStream1.available()];

        if(inputStream1.available()>0)  inputStream1.read(buffer1);
        inputStream1.close();

        String dobl="";
        ArrayList<Double> doubleArrayList = new ArrayList<>();

        for (byte aBuffer1 : buffer1) {

            if (aBuffer1 != 32) dobl += (char) (aBuffer1);
            else {
                doubleArrayList.add(Double.parseDouble(dobl));
                dobl = "";
            }
        }
        if(!dobl.equals("")) doubleArrayList.add(Double.parseDouble(dobl));

        FileOutputStream outputStream = new FileOutputStream(file2);

        for(Double in : doubleArrayList){
            Long val = Math.round(in);
            outputStream.write(val.toString().getBytes());
            outputStream.write(32);
        }
        outputStream.close();

    }
}
