package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
            reader.close();

        }catch (IOException e){

        }
    }
    //add your code here - добавьте код тут

    public static void main(String[] args) throws InterruptedException  {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException  {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface{

        private String fileName="";
        private String content="";
        private String finalString="";


        @Override
        public void setFileName(String fullFileName) {
            this.fileName=fullFileName;
        }

        @Override
        public String getFileContent() {

            return finalString;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));

                while ((content = reader.readLine()) != null ) {
                    finalString += content + " ";
                }
                reader.close();

            }catch (IOException e){}

        }

    }

    //add your code here - добавьте код тут
}
