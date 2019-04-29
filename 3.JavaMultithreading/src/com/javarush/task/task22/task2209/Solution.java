package com.javarush.task.task22.task2209;

import java.io.*;
import java.util.ArrayList;

/*
��������� ������� ����
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

        if(words!=null && words.length!=0) {   // �������� �� ������� ����������

            ArrayList<StringBuilder> megaList = new ArrayList<>(); // ��� ������ ��� ��������� ����������

            ArrayList<String> originalList = new ArrayList<>();  // ������������ ���� ��������

            for (String word : words) {
                originalList.add(word.toLowerCase()); // ����� �� � ��������� �����
            }

            int k = originalList.size();

            while (k > 0) {   //  ��������� ��� ������� ���, ������� � ��� ��������� � ������������ �������
                StringBuilder stringBuilder = new StringBuilder();
                ArrayList<String> copyOfList = new ArrayList<>(originalList);  // ����� ������������� �����

                ArrayList<String> list = new ArrayList<>();  // ���� ��� � ����� ����������� �����

                list.add(copyOfList.get(k-1));  // ������� � ����� (�� �����) ����� �������� ���� (���������) � ����
                copyOfList.remove(k-1);  // � ������ ��� �� ����� ����� ���������

                int n = copyOfList.size();
                while (n > 0) {  //  ��������� ��� ������� ���, ������� � ��� ��������� � ������������ �������

                    //��� ����������� �����, ���� ���� ����������, �� �������� �� ����� ����� ������ ��� �������� index
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


                // ���������� ��������� ����� � ���������� stringBuilder
                for (String into : list) {
                    String str = into;
                    str = Character.toUpperCase(str.charAt(0)) + str.substring(1);

                    stringBuilder.append(str).append(" ");
                }
                // ������� ������
                stringBuilder.replace(stringBuilder.length()-1,stringBuilder.length(),"");
                megaList.add(stringBuilder);

                k--;
            }

            // ��� � ������ ������ ����� ������� ����������
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
