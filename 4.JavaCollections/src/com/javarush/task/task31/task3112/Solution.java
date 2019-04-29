package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {

        int index = urlString.lastIndexOf('/')+1;
        String name = urlString.substring(index);

        URL url = new URL(urlString);
        InputStream inputStream = url.openStream();

        Path templFile = Files.createTempFile("temp-1",".tmp");
        Files.copy(inputStream,templFile);

        Path direct = Paths.get(downloadDirectory+"/"+name);
        Path path = Files.move(templFile,direct);

        // implement this method
        return path;
    }
}
