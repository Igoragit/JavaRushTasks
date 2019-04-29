package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.*;

/*
Проход по дереву файлов
*/
public class Solution {

    static ArrayList<File> filePaths = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        getAllFiles(path);
        Collections.sort(filePaths);

        FileOutputStream outputStream = new FileOutputStream(allFilesContent);
        try {

            for (File in: filePaths ){

                FileInputStream fileInputStream = new FileInputStream(in);
                while (fileInputStream.available()>0){
                    outputStream.write(fileInputStream.read());
                }
                outputStream.write('\n');

                fileInputStream.close();
            }

            outputStream.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            outputStream.close();
        }


    }



    public static   void getAllFiles(File path){

        if(path.isDirectory()){
            try {
                for(File in: path.listFiles()){

                    if(in.isDirectory()){
                       getAllFiles(in);
                    }else {

                        File currentFile = new File(in.getAbsolutePath());

                       if(currentFile.length()<=50){
                           filePaths.add(currentFile);
                       }
                    }
                }
            }catch (NullPointerException e){
                System.out.println(e.getMessage());
            }
        }

    }

}
