package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {


        BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
        FileWriter writer1 = new FileWriter(args[1]);

       // BufferedReader fileReader = new BufferedReader(new FileReader("G:/file.txt"));
        //FileWriter writer1 = new FileWriter("G:/file12.txt");

        ArrayList<String> strings = new ArrayList<>();
        while (fileReader.ready()){
            strings.add(fileReader.readLine());
        }
        fileReader.close();

        ArrayList<String> allStrings = new ArrayList<>();

        for (String in: strings){

            String[] spl = in.split(" ");

            for(String into: spl){
                if(into.length()>6) allStrings.add(into);
            }
        }


        boolean ch = true;
        for(int q = 0; q < allStrings.size(); q ++){

            if(q==allStrings.size()-1) ch=false;

                writer1.write(allStrings.get(q));
                if(ch) writer1.write(",");

        }
            writer1.close();

    }
}
