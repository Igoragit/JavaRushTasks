package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;

/*
Составить цепочку слов
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        //...

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        FileReader fileReader = new FileReader(fileName);


        StringBuilder file = new StringBuilder();
        while (fileReader.ready()){
            file.append((char) fileReader.read());
        }


        String[] strings = file.toString().split(" ");

        for (int x = 0; x< strings.length; x++){
           strings[x] = strings[x].toLowerCase();
        }

        StringBuilder result = getLine(strings);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {

        if(words!=null && words.length!=0) {   // проверка на условие валидатора

            ArrayList<StringBuilder> megaList = new ArrayList<>(); // тут хранию все возможные комбинации

            ArrayList<String> originalList = new ArrayList<>();  // оригинальный лист стрингов

            for (String word : words) {
                originalList.add(word.toLowerCase()); // делаю всё с маленькой буквы
            }

            int k = originalList.size();

            while (k > 0) {   //  повторяем цик столько раз, сколько у нас элементов в оригинальном массиве
                StringBuilder stringBuilder = new StringBuilder();
                ArrayList<String> copyOfList = new ArrayList<>(originalList);  // копия оригинального листа

                ArrayList<String> list = new ArrayList<>();  // лист где я храню подобранные слова

                list.add(copyOfList.get(k-1));  // начинаю с конца (не важно) сразу добавляю один (последний) в лист
                copyOfList.remove(k-1);  // и удаляю его из копии листа оригинала

                int n = copyOfList.size();
                while (n > 0) {  //  повторяем цик столько раз, сколько у нас элементов в оригинальном массиве

                    //тут стравниваем буквы, если есть совпадение, то удалячем из копии листа объект под индексом index
                    int index = -1;
                    for (int x = 0; x < copyOfList.size(); x++) {

                        String str1 = copyOfList.get(x);
                        String str2 = list.get(list.size() - 1);

                        char ch1 = str1.charAt(0);
                        char ch2 = str2.charAt(str2.length() - 1);

                        if (ch1 == ch2) {
                            list.add(str1);
                            index = x;
                            break;
                        }
                    }

                    if (index != -1) {
                        copyOfList.remove(index);
                    }

                    n--;
                }


                // возвращаем заглавные буквы и морфмируем stringBuilder
                for (String into : list) {
                    String str = into;
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);

                    stringBuilder.append(str).append(" ");
                }
                // удаляем пробел
                stringBuilder.replace(stringBuilder.length()-1,stringBuilder.length(),"");
                megaList.add(stringBuilder);

                k--;
            }

            // тут я просто нахожу самую длинную комбинацию
            int maxIndex=0;
            int lenght=megaList.get(0).length();

            for (int e = 1; e < megaList.size(); e++ ){
                if(megaList.get(e).length()>=lenght) {
                    lenght = megaList.get(e).length();
                    maxIndex = e;
                }
            }

          //  System.out.println(megaList);
            return megaList.get(maxIndex);
        }

    return new StringBuilder();
    }
}
