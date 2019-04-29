package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;


public class Solution {
    public static void main(String[] args) throws IOException {

        //считываем 2 имени файла
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        reader.close(); // закрываем поток

        // создаем как и хочет валидаторы BufferedReader и BufferedWriter с конструкторами FileReader и FileWriter
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        //считываем из файла
        String fileInfo="";

        while (fileReader.ready()){
            fileInfo = fileReader.readLine();



            //разбираем на массив убирая всё, что не является цифровым символом
            String[] numS = fileInfo.split(" ");

            // переписываем в лист Integer

            for(String in: numS){
                try {
                    integerArrayList.add(Integer.parseInt(in));
                } catch (NumberFormatException e){

                }
            }
        }

        boolean ch= true; // флаг, чтобы не записать лишний пробел в конце
        for(int x =0; x < integerArrayList.size(); x++){

            // берем из листа, конвертируем в стринг и записываем
            fileWriter.write(integerArrayList.get(x).toString());
            if(ch) fileWriter.write(" "); // добавляем пробел
            // меняем флаг на false, перед последним входом в цыкл
            if(x==integerArrayList.size()-2) ch=false;
        }


        //закрываем потоки
        fileReader.close();
        fileWriter.close();

    }
/*
    public static void write(BufferedWriter writer, String[] strings) throws IOException {

        boolean check= true;
        for(int x =0; x < strings.length; x++) {

            if (!strings[x].equals("")) {
                char[] chars = strings[x].toCharArray();
                String dig = "";
                for (char ch : chars) {

                    if (Character.isDigit(ch)) {
                        dig += ch;
                    }
                }

                if(!dig.equals("")){
                    writer.write(dig);

                }


                if (check) writer.write(" ");
                if (x == strings.length - 3) check = false;

            }
        }

    }
    */
}
