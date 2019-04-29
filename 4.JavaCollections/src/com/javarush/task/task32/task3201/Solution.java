package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.RandomAccess;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {

        RandomAccessFile randomAccess = new RandomAccessFile(args[0],"rw");

        long lenght = randomAccess.length();
        long position = Long.valueOf(args[1]);

        if(position>lenght) position=lenght;


        randomAccess.seek(position);
        randomAccess.write(args[2].getBytes());




    }
}
