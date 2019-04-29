package com.javarush.task.task33.task3303;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* 
Десериализация JSON объекта
*/
public class Solution {
    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        T res=null;
        res = (T)(objectMapper.readValue(new FileReader(new File(fileName)),clazz));

        return res;
    }

    public static void main(String[] args) {

    }
}
