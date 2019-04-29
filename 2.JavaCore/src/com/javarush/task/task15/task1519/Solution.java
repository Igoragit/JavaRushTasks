package com.javarush.task.task15.task1519;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/* 
Разные методы для разных типов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str="";
        ArrayList<String> stringArrayList = new ArrayList<>();

        Character[] characters = new Character[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


        while (true){

            if(!(str =reader.readLine()).equals("exit")){
                stringArrayList.add(str);
            }
            else break;
        }

        for (String s: stringArrayList){
            boolean isNum=false;
            boolean isdot=false;
            boolean isMinus=false;
            int count=0;


            for(Character numbers: characters ){

                for(char ch: s.toCharArray()) {

                    if (numbers.equals(ch)) {
                        isNum = true;
                        count++;
                    }
                    if (s.contains(".")) isdot = true;
                    if (s.contains("-")) isMinus = true;
                }
            }


            if(!isdot){
                if(!isMinus){
                    if(isNum) {
                        if(count==s.length()) {
                            if (Integer.parseInt(s) > 0 && Integer.parseInt(s) < 128) print(Short.parseShort(s));
                            else print(Integer.parseInt(s));
                        }

                        else print(s);
                    }
                    else print(s);
                }


                else {

                    if(isNum) {
                        if(count+1>=s.length()) {
                            if(s.charAt(0)=='-') print(Integer.parseInt(s));
                            else print(s);
                        }

                        else print(s);
                    }
                    else print(s);

                }
            }
            else {

                if(!isMinus){
                    if(isNum) {
                        if(count+1>=s.length()) {
                            if(s.charAt(0)!='.') print(Double.parseDouble(s));
                            else print(s);
                        }

                        else print(s);
                    }
                    else print(s);
                }


                else {

                    if(isNum) {
                        if(count+2>=s.length()) {
                            if(s.charAt(0)=='-' && s.charAt(0)!='.') print(Double.parseDouble(s));
                            else print(s);
                        }

                        else print(s);
                    }
                    else print(s);

                }
            }


            }


        }


    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип String, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
