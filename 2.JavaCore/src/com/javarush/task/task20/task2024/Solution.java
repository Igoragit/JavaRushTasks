package com.javarush.task.task20.task2024;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/* 
Знакомство с графами
*/
public class Solution implements Serializable {
    int node;
    List<Solution> edges = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        File file = new File("G:/file.txt");
        OutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);



        Solution sol = new Solution();
        objectOutputStream.writeObject(sol.edges);
        objectOutputStream.flush();
        objectOutputStream.close();



    }
}
