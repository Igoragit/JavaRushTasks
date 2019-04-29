package com.javarush.task.task39.task3904;

import java.util.HashMap;

/* 
Лестница
*/
public class Solution {
    private static int n = 4;
    static HashMap<Integer, Long> valRes = new HashMap<>();

    static {
        valRes.put(0, 1L);
        valRes.put(1, 1L);
        valRes.put(2, 2L);
        valRes.put(3, 4L);
    }

    public static void main(String[] args) {
        System.out.println("Number of possible runups for " + n + " stairs is: " + numberOfPossibleAscents(n));
        System.out.println("");
    }

    public static long numberOfPossibleAscents(int n) {
        if (n < 0) return 0;

        if (valRes.containsKey(n)) return valRes.get(n);

        else {
            long fibonacciValue = ((numberOfPossibleAscents(n - 3) + numberOfPossibleAscents(n - 2)) + numberOfPossibleAscents(n - 1));
            valRes.put(n, fibonacciValue);
            return fibonacciValue;
        }
    }
}

