package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class Solution {
    public static void main(String[] args) throws IOException {

        //String[] args1 = new String[1];
        //args1[0]="7";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();

        FileInputStream inputStream = new FileInputStream(file);

        byte[] buffer = new byte[inputStream.available()];

        inputStream.read(buffer);
        inputStream.close();

        String buff = new String(buffer, "UTF-8");
        String[] splittedBuffer = buff.split("\r\n");


      //  System.out.println(buffer);


        Map<Integer,String> integerStringHashMap = createMapIdString(splittedBuffer);

        for(Map.Entry<Integer,String> in: integerStringHashMap.entrySet()){

            if(String.valueOf(in.getKey()).equals(args[0])){
                System.out.println(in.getValue());
            }
        }
    }


    public static Map<Integer,String> createMapIdString(String[] buffer){

        Map<Integer,String> integerStringHashMap = new HashMap<>();

        for(String in: buffer){

            if(in.equals("")) continue;
             String[] line =  in.split(" ");
             String info="";

             for(int x=1; x< line.length; x++){

                 info+=line[x] + " ";
             }

             integerStringHashMap.put(Integer.parseInt(line[0]),info);

        }

        return integerStringHashMap;
    }
}
