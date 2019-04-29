package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

      BufferedReader reader = new BufferedReader(new FileReader(args[0]));
      //  BufferedReader reader = new BufferedReader(new FileReader("G:/file.txt"));


        ArrayList<String> name = new ArrayList<>();
        ArrayList<Double> znach = new ArrayList<>();

        HashMap<String,Double> hashMap = new HashMap<>();


        while (reader.ready()){


            String str = reader.readLine();

            if(str!="") {
                String[] splittet = str.split(" ");

                name.add(splittet[0]);
                znach.add(Double.parseDouble(splittet[1]));
            }
        }

        reader.close();


        for (int x =0; x<name.size(); x++){

            if(!hashMap.containsKey(name.get(x))) {
                hashMap.put(name.get(x),znach.get(x));
            }
            else {
                double old = hashMap.get(name.get(x));
                hashMap.replace(name.get(x),old+znach.get(x));
            }
        }

        List<Map.Entry<String,Double>> entryList = new ArrayList<>(hashMap.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        for (Map.Entry<String,Double> in: entryList){

            System.out.println(in.getKey()+ " " + in.getValue());
        }

    }
}
