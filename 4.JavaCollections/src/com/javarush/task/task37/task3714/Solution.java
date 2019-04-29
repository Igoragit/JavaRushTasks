package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        final  char symbol[] = {'M','D','C','L','X','V','I'};
        final  int   value[] = {1000,500,100,50,10,5,1};

            s = s.toUpperCase();
            if(s.length() == 0) return 0;
            for(int i = 0; i < symbol.length; i++)
            {
                int pos = s.indexOf(symbol[i]) ;
                if(pos >= 0){
                    int re1 = romanToInteger(s.substring(0,pos));
                    int re2 = romanToInteger(s.substring(pos+1));
                    return value[i] - re1 + re2;
                }
            }
            throw new IllegalArgumentException("Wrong input ");
    }
}
