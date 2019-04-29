package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        //напишите тут ваш код

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();

        stringIntegerHashMap.put("qwe", 12);
        stringIntegerHashMap.put("qwer", 122);
        stringIntegerHashMap.put("qwert", 13);
        stringIntegerHashMap.put("qwerty", 124);
        stringIntegerHashMap.put("qwertyu", 156);
        stringIntegerHashMap.put("qwertyui", 1676);
        stringIntegerHashMap.put("qwertyuio", 1345);
        stringIntegerHashMap.put("qwertyuiop", 12);
        stringIntegerHashMap.put("asd", 1245);
        stringIntegerHashMap.put("qwesadf", 412);


        return stringIntegerHashMap;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        //напишите тут ваш код

        map.entrySet().removeIf(stringIntegerEntry -> stringIntegerEntry.getValue() < 500);

    }

    public static void main(String[] args) {


    }
}