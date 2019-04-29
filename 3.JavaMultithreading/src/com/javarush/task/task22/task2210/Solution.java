package com.javarush.task.task22.task2210;

import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {


     //   System.out.println(getTokens("asdasdasd.asdasdasd.gsdfg3rg.","."));

    }
    public static String [] getTokens(String query, String delimiter)    {


        StringTokenizer stringTokenizer = new StringTokenizer(query,delimiter);
        String[] str = new String[stringTokenizer.countTokens()];
        int n=0;
        while (stringTokenizer.hasMoreTokens()){

           str[n]= stringTokenizer.nextToken();

                   n++;
        }

        return str;
    }
}
