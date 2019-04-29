package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles= new ArrayList<>();


    private boolean isPartOfName=false;
    private boolean isPartOfContent =false;
    private boolean isMaxSize = false;
    private boolean isMinSize=false;


    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        byte[] content = Files.readAllBytes(file); // размер файла: content.length


        boolean isResult = true;

        if(isPartOfName && isResult){
            isResult =file.getFileName().toString().contains(partOfName);
        }

        String contentStr = new String(content);
        if(isPartOfContent && isResult ){
            isResult =  contentStr.contains(partOfContent);
        }

        if(isMaxSize && isResult){
            isResult = content.length<maxSize;
        }

        if(isMinSize && isResult){
            isResult = content.length>minSize;
        }


            if(isResult){
                foundFiles.add(file);

        }


        return super.visitFile(file, attrs);
    }

    public String getPartOfName() {
        return partOfName;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
        this.isPartOfName=true;
    }

    public String getPartOfContent() {
        return partOfContent;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
        this.isPartOfContent = true;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
        this.isMinSize = true;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
        this.isMaxSize=true;
    }

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setFoundFiles(List<Path> foundFiles) {
        this.foundFiles = foundFiles;
    }
}
