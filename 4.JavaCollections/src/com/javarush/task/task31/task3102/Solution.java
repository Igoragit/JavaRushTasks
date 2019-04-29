package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {

        List<String> filePaths = new ArrayList<>();

        File rootFolder = new File(root);

        Queue<File> fileQueue = new PriorityQueue<>();

        Collections.addAll(fileQueue,rootFolder.listFiles());

        while (!fileQueue.isEmpty()){

            File currentFile = fileQueue.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(fileQueue,currentFile.listFiles());
            }else {
                filePaths.add(currentFile.getAbsolutePath());
            }
        }

        return filePaths;

    }

    public static void main(String[] args) {
        
    }
}
