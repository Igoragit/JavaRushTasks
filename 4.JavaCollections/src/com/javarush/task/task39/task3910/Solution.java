package com.javarush.task.task39.task3910;

import java.math.BigDecimal;

/*
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPowerOfThree(9));
    }

    public static boolean isPowerOfThree(int n) {
        if (n == 0) return false;
        while ((n%3)==0){
            n=n/3;
        }
        return n == 1;
    }

}
