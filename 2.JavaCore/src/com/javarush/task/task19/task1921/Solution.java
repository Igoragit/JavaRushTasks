package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws IOException {

       BufferedReader reader = new BufferedReader(new FileReader(args[0]));
      //  BufferedReader reader = new BufferedReader(new FileReader("G:/file.txt"));


        ArrayList<String> fileData = new ArrayList<>();

        while (reader.ready()){
            fileData.add(reader.readLine());
        }

        reader.close();

        for(String in: fileData){

            PEOPLE.add(createPerson(in));
        }

       // System.out.println("asd");

    }

    public static Person createPerson (String info){

        String[] splitted = info.split(" ");
        int size = splitted.length;


        String name=splitted[0];
        for(int x = 1; x < size - 3; x ++ ){
            name += " " + splitted[x];
        }

        Calendar calendar = new GregorianCalendar(Integer.parseInt(splitted[size-1]), Integer.parseInt(splitted[size-2])-1, Integer.parseInt(splitted[size-3]));
        Date date = calendar.getTime();
        return new Person(name,date);



    }

}
