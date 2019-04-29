package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {


       // FileInputStream fileInputStream = new FileInputStream(args[0]);
        FileInputStream fileInputStream = new FileInputStream("J:/file13.txt");

        int count=0;
        while (fileInputStream.available()>0){
           int b = fileInputStream.read();

            if (((b >= 'a')&&(b <= 'z')) || ((b >= 'A')&&(b <= 'Z'))){
                count++;
            }
        }
        fileInputStream.close();
        System.out.println(count);

    }
}
