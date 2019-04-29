package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.util.HashMap;

public class HashMapStorageStrategy implements StorageStrategy {

    private HashMap<Long, String>  data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key,value);
    }

    @Override
    public Long getKey(String value) {
        for (Long in: data.keySet()){
            data.get(in).contains(value);
            return in;
        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        return data.get(key);
    }
}
