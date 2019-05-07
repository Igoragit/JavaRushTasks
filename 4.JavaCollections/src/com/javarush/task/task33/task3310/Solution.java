package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String[] args) {

        StorageStrategy strategy = new HashMapStorageStrategy();
        testStrategy(strategy, 10000);

    }

    static Set<Long> getIds(Shortener shortener, Set<String> strings){

        Set<Long> ids = new HashSet<>();

        for (String in: strings){
            ids.add(shortener.getId(in));
        }

        return ids;
    }

    static Set<String> getStrings(Shortener shortener, Set<Long> keys){

        Set<String> strings = new HashSet<>();

        for (Long in: keys){
            strings.add(shortener.getString(in));
        }

        return strings;
    }

    static void testStrategy(StorageStrategy strategy, long elementsNumber){
        System.out.println(strategy.getClass().getSimpleName());

        Set<String> strings = new HashSet<>();
        for (int x =0; x<elementsNumber; x++){
            strings.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        ///
        Date startTime = new Date();
        Set<Long> ids = getIds(shortener, strings);
        Date endTime = new Date();

        long deltaTime = endTime.getTime() - startTime.getTime();
        Helper.printMessage(Long.toString(deltaTime));

        ///
        startTime = new Date();
        Set<String> strs = getStrings(shortener, ids);
        endTime = new Date();

        deltaTime = endTime.getTime() - startTime.getTime();
        Helper.printMessage(Long.toString(deltaTime));

        if (strings.equals(strs))
            Helper.printMessage("Тест пройден.");
        else
            Helper.printMessage("Тест не пройден.");
    }
}
