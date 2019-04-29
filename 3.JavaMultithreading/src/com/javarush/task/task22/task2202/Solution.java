package com.javarush.task.task22.task2202;

/* 
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
       // System.out.println(getPartOfString("JavaRush -    лучший сервис    обучения Java."));
     //   System.out.println(getPartOfString("JavaRush."));
    }

    public static String getPartOfString(String string) {

        if(string!=null) {
            int index = string.indexOf(' ');
            int indexEnd = 0;
            int count = 0;
            char[] chars = string.toCharArray();


            for (int x = 0; x < chars.length; x++) {

                if (count == 4) {
                    indexEnd = x;
                    break;
                }

                if (chars[x] == ' ') {
                    count++;
                }
            }

            int inDex=0;
            for (int q= indexEnd+1; q < chars.length; q++ ){

              
            }

            if (count < 4) throw new TooShortStringException();


            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(chars, index, indexEnd - index);
            String res = stringBuilder.toString();


            return res;
        }

        return null;
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
