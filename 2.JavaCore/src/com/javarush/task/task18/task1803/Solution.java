package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.util.*;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();

        ArrayList<Byte> bytes = new ArrayList<>();
        Map<Byte,Integer>  byteIntegerMap = new HashMap<>();

        FileInputStream inputStream = new FileInputStream(file);
        while (inputStream.available()>0){

            bytes.add((byte)inputStream.read());
        }


        int cout=1;
        for(int x = 0; x < bytes.size(); x ++){

            for(int y = x+1; y < bytes.size(); y ++){
                if(Objects.equals(bytes.get(x), bytes.get(y))) cout++;
            }

            if(!byteIntegerMap.containsKey((bytes.get(x)))) byteIntegerMap.put(bytes.get(x),cout);
            cout=1;
        }
    /*
        Iterator<Map.Entry<Byte,Integer>> iterator = byteIntegerMap.entrySet().iterator();

        int frirst = (int)iterator.next().getValue();

        while (iterator.hasNext()){
            for(Map.Entry<Byte,Integer> bi: byteIntegerMap.entrySet()){
            }
        }
    */


        List<Map.Entry<Byte, Integer>> list = new ArrayList<>(byteIntegerMap.entrySet());
        list.sort(new Comparator<Map.Entry<Byte, Integer>>() {
            @Override
            public int compare(Map.Entry<Byte, Integer> object1, Map.Entry<Byte, Integer> object2) {
                return object1.getValue().compareTo(object2.getValue());
            }
        });

        boolean chechMaxCount=true;
        int index=list.size()-1;
        while (Objects.equals(list.get(index).getValue(), list.get(index - 1).getValue())){
            index--;
        }

        for(int i = index; i<= list.size()-1; i++){
            System.out.println(list.get(i).getKey());
        }




     //   System.out.println(list);
        System.out.println(byteIntegerMap);

    }
}
