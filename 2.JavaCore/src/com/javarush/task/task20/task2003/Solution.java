package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        reader.close();
        InputStream inputStream = new FileInputStream(fileName);
        load(inputStream);

        //implement this method - реализуйте этот метод
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        PrintWriter writer = new PrintWriter(outputStream);

        Properties SysSysProperties = new Properties();

        for(Map.Entry<String,String> in: properties.entrySet()){
            SysSysProperties.put(in.getKey(),in.getValue());
        }

        SysSysProperties.store(writer,null);
        writer.close();

    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        Properties SysProperties = new Properties();
        SysProperties.load(inputStream);

        for(Map.Entry<Object,Object> in: SysProperties.entrySet()){
            properties.put(in.getKey().toString(),in.getValue().toString());
        }

        reader.close();
    }

    public static void main(String[] args) {

    }
}
