package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Byte> bytes = new ArrayList<>();
        Map<Byte,Integer> byteIntegerMap = new HashMap<>();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str = reader.readLine();
        reader.close();



        FileInputStream inputStream;

        if(str!=null && !str.equals("")) {
            inputStream = new FileInputStream(str);

            while (inputStream.available()>0){
                bytes.add((byte)inputStream.read());
            }
            inputStream.close();
        }

        int cout=1;
        for(int x = 0; x < bytes.size(); x ++){

            for(int y = x+1; y < bytes.size(); y ++){
                if(Objects.equals(bytes.get(x), bytes.get(y))) cout++;
            }

            if(!byteIntegerMap.containsKey((bytes.get(x)))) byteIntegerMap.put(bytes.get(x),cout);
            cout=1;
        }



        if(!byteIntegerMap.isEmpty()) {
            List<Map.Entry<Byte, Integer>> list = new ArrayList<>(byteIntegerMap.entrySet());


            list.sort(new Comparator<Map.Entry<Byte, Integer>>() {
                @Override
                public int compare(Map.Entry<Byte, Integer> object1, Map.Entry<Byte, Integer> object2) {
                    return object1.getKey().compareTo(object2.getKey());
                }
            });

            for(Map.Entry<Byte,Integer> finalList: list){
                System.out.print(finalList.getKey() + " ");
            }
        }
    }
}

