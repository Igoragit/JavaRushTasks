package com.javarush.task.task39.task3908;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("abcabacb"));
    }

    public static boolean isPalindromePermutation(String s) {

        String str = s.toLowerCase();
        int count[] = new int[256]; //ASCII - 256
        Arrays.fill(count, 0);

        for (int i = 0; i < str.length(); i++)
            count[(int)(str.charAt(i))]++;

        int odd = 0;
        for (int i = 0; i < 256; i++)
        {
            if((count[i]%2!=0)){
                odd++;
            }

            if (odd > 1)
                return false;
        }

        return true;
/*
        Set<Character> oddLetters = new HashSet<>();
        for ( char c : s.toCharArray() ) {
            if ( ! oddLetters.remove(c) ) {
                oddLetters.add(c);
            }
        }
        return oddLetters.size() <= 1;*/
    }
}
