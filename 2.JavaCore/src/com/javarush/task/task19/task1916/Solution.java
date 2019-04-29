package com.javarush.task.task19.task1916;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {


        BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
        String file1 = file.readLine();
        String file2 = file.readLine();
        file.close();

        BufferedReader  file1Reader = new BufferedReader(new FileReader(file1));
        BufferedReader  file2Reader = new BufferedReader(new FileReader(file2));


        ArrayList<String> file1Array = new ArrayList<>();
        ArrayList<String> file2Array = new ArrayList<>();


        while (file1Reader.ready()){
            file1Array.add(file1Reader.readLine());
        }

        while (file2Reader.ready()){
            file2Array.add(file2Reader.readLine());
        }

        file1Reader.close();
        file2Reader.close();

        int count=0;
        if(file1Array.size()>file2Array.size()) count=file1Array.size();
        if(file1Array.size()<file2Array.size()) count=file2Array.size();


        if (file1Array.size() > file2Array.size()) {
            int r = file1Array.size()-file2Array.size();

            for(int q =0; q<r; q++){
                file2Array.add("");
            }
        }

        for(int x =0; x < count; x ++ )
        {

                if (x == count - 1 && file1Array.size() > file2Array.size()) {
                    file2Array.add("");
                    file2Array.add("");
                }

                if (x == count - 1 && file2Array.size() > file1Array.size()) {
                    file1Array.add("");
                    count = file1Array.size();
                }


                if (file1Array.get(x).equals(file2Array.get(x))) {
                    lines.add(new LineItem(Type.SAME, file1Array.get(x)));
                }

                if (!file1Array.get(x).equals(file2Array.get(x)) && !file1Array.get(x).equals(file2Array.get(x + 1))) {

                    lines.add(new LineItem(Type.REMOVED, file1Array.get(x)));
                    file2Array.add(x, "");
                }

                if (!file1Array.get(x).equals(file2Array.get(x)) && file1Array.get(x).equals(file2Array.get(x + 1))) {
                    lines.add(new LineItem(Type.ADDED, file2Array.get(x)));
                    file1Array.add(x, "");
                }

                count = file1Array.size();
            }



        for(LineItem in:lines){
            System.out.println(in.type + " " + in.line);
     }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
