package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        writeFromTwoFileToFile(file1, file2, file3);

    }

     static  void writeFromTwoFileToFile(String toFile, String fromfile1, String fromfile2){

        try {
            FileInputStream fileInputStream = new FileInputStream(fromfile1);
            FileInputStream fileInputStream2 = new FileInputStream(fromfile2);
            FileOutputStream fileOutputStream = new FileOutputStream(toFile);


            try {

                while (fileInputStream.available()>0){
              fileOutputStream.write(fileInputStream.read());
                }

                while (fileInputStream2.available()>0){
                    fileOutputStream.write(fileInputStream2.read());
                }

                fileInputStream.close();
                fileInputStream2.close();
                fileOutputStream.close();


            } catch (IOException e){
                System.out.println("IOException");
            }

        } catch (FileNotFoundException e){
            System.out.println("FileNotFoundException");
        }
    }
}
