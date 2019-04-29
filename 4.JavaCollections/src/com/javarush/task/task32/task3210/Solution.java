package com.javarush.task.task32.task3210;

import com.javarush.task.task31.task3101.FileUtils;

import java.io.*;
import java.util.Objects;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws IOException {


        File allFilesContent = new File("C:\\Users\\Gor\\Desktop\\allFilesContent.txt");
        System.out.println(FileUtils.isExist(allFilesContent));
        System.out.println(allFilesContent.exists());

        FileWriter fileWriter = new FileWriter(allFilesContent);
        fileWriter.write("sdfsdf");

       /* String filePath = args[0];
        int posStart = Integer.parseInt(args[1]);
        String textToCompareWith = args[2];

        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            byte[] buf = new byte[textToCompareWith.length()];

            String textFromFile = new String(buf); file.seek(posStart);
            file.read(buf, 0, buf.length);
            String resultStringToWright = textFromFile.equals(textToCompareWith) ? "true" : "false";
            file.seek(file.length());
            file.write(resultStringToWright.getBytes());
        }*/
    }
}
