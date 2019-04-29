package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Что внутри папки?
*/
public class Solution {

    private static int folderCount=0;
    private static int filesCount=0;
    private static long directSizeByte=0;


    public static void main(String[] args) throws IOException {

        BufferedReader consolReader = new BufferedReader(new InputStreamReader(System.in));
        String pathStr = consolReader.readLine();

        File file = new File(pathStr);


        if(!file.isDirectory()){
            System.out.println(file.getPath()+" - не папка");
            System.exit(0);
        }else {

            getAllFiles(file);
        }

        System.out.println("Всего папок - "+folderCount );
        System.out.println("Всего файлов - "+filesCount );
        System.out.println("Общий размер - "+directSizeByte );

    }


    public static   void getAllFiles(File path){

        if(path.isDirectory()){
            try {
                for(File in: path.listFiles()){

                    if(in.isDirectory()){
                        folderCount++;
                        getAllFiles(in);
                    }else {
                        filesCount++;
                        File currentFile = new File(in.getAbsolutePath());
                        directSizeByte+=currentFile.length();
                    }
                }
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
