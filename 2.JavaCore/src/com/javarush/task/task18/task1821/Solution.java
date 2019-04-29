package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
       // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //FileInputStream inputStream = new FileInputStream(reader.readLine());

        byte[] buffer = new byte[inputStream.available()];
        inputStream.read(buffer);
        inputStream.close();

        Map<Byte,Integer> byteIntegerMap = new HashMap<>();

        for (byte in: buffer){
            int count=0;
            for(int x =0; x<buffer.length; x++){

                if(in==buffer[x]){
                    count++;
                }
            }

            if(!byteIntegerMap.containsKey(in)){
                byteIntegerMap.put(in,count);
            }
        }

        if(!byteIntegerMap.isEmpty()){

            List<Map.Entry<Byte,Integer>> BIList = new ArrayList<>(byteIntegerMap.entrySet());

            BIList.sort(new Comparator<Map.Entry<Byte, Integer>>() {
                @Override
                public int compare(Map.Entry<Byte, Integer> o1, Map.Entry<Byte, Integer> o2) {
                    return o1.getKey().compareTo(o2.getKey());
                }
            });

            for(Map.Entry<Byte,Integer> in:BIList){
                byte getByte = in.getKey();
                System.out.println((char)getByte + " " + in.getValue());
            }
        }
    }
}
