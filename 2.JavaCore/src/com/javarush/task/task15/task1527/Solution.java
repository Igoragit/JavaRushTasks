package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        //add your code here
      // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       //String url = reader.readLine();
      // reader.close();

        Scanner in = new Scanner(System.in);
        String url = in.next(); //(НЕ in.nextLine()!!!)
        in.close();


        if(!url.isEmpty()) {
            ArrayList<Character> characters = new ArrayList<>();

            //разбираем всё на char выкидывая лишнее с начала ссылки до знака ?
            boolean ifParam = false;
            for (Character ch : url.toCharArray()) {
                if (ifParam) characters.add(ch);
                if (ch == '?') ifParam = true;
            }

            //разбиваем на первоначальные составные части типа: lvl=15
            //&
            //view и т.д.
            ArrayList<String> listOfParams = new ArrayList<>();
            String makeParam = "";
            for (int q = 0; q < characters.size(); q++) {

                if (characters.get(q) != '&') makeParam += characters.get(q);
                else {
                    listOfParams.add(makeParam);
                    listOfParams.add("&");
                    makeParam = "";
                }
                if (q == characters.size() - 1) listOfParams.add(makeParam);
            }

            //разбиваем на составные части полностью
            //lvl
            //=
            //15
            // и т д.

            ArrayList<String> listOfParamsEnd = new ArrayList<>();
            String makeParamEnd = "";

            if(!listOfParams.isEmpty()) {
                for (String st : listOfParams) {

                    for (int c = 0; c < st.toCharArray().length; c++) {

                        if (st.toCharArray()[c] != '=') makeParamEnd += st.toCharArray()[c];
                        else {
                            listOfParamsEnd.add(makeParamEnd);
                            listOfParamsEnd.add("=");
                            makeParamEnd = "";
                        }
                        if (c == st.toCharArray().length - 1) {
                            listOfParamsEnd.add(makeParamEnd);
                            makeParamEnd = "";
                        }
                    }
                }
            }

            if(!listOfParamsEnd.isEmpty()) {
                // генерим string параметров
                String resPar = listOfParamsEnd.get(0);
                for (int x = 2; x < listOfParamsEnd.size(); x++) {

                    if (listOfParamsEnd.get(x).equals("=")) continue;
                    if (listOfParamsEnd.get(x - 1).equals("=")) continue;
                    if (listOfParamsEnd.get(x).equals("&")) continue;
                    if (listOfParamsEnd.get(x - 1).equals("&")) resPar += " " + listOfParamsEnd.get(x);


                }

                System.out.println(resPar);

                // поиск объектов и вызов методов
                for (int x = 0; x < listOfParamsEnd.size(); x++) {

                    if (listOfParamsEnd.get(x).equals("obj")) {

                        try {
                            double dobVal = Double.parseDouble(listOfParamsEnd.get(x + 2));
                            alert(dobVal);
                        } catch (NumberFormatException e) {
                            alert(listOfParamsEnd.get(x + 2));
                        }
                    }
                }
            }

        }
    }


    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
