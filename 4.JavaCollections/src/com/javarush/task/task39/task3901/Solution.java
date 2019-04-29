package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {

        if(s==null ||s.length()==0) return 0;

        int length = 0;
        int max=0;

        Set<Character> characters = new HashSet<>();
        for (int x=0; x<s.length(); x++){
            char e = s.charAt(x);
            if(!characters.contains(e)){
                characters.add(e);
                length++;
                if(max<length)  max=length;
            }
            else{

                length=1;
                characters.clear();
                characters.add(e);
            }
        }
        return max;
    }
}
