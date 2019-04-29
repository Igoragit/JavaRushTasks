package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) {

        int mn[][] = {{1, 0, 1, 1, 1},
                      {1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1}};
        System.out.println(maxSquare(mn));
    }

    public static int maxSquare(int[][] matrix) {

        int lenght = (matrix.length >matrix[0].length)?matrix.length : matrix[0].length;
        int resMat[][] = new int[lenght+1][lenght+1];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < lenght; j++) {
                if (matrix[i][j] == 0) {
                    resMat[i + 1][j + 1] = 0;
                }
                if (matrix[i][j] == 1) {
                    int arr[] = new int[3];
                    arr[0] = resMat[i][j];
                    arr[1] = resMat[i][j + 1];
                    arr[2] = resMat[i + 1][j];
                    int min = min(arr);
                    //System.out.println("arr" + arr[0] + arr[1] + arr[2]+ "min" + min);
                    resMat[i + 1][j + 1] = min + 1;
                }
            }
        }

        int maxVals[] = new int[lenght+1];
        for (int i = 0; i < lenght+1; i++) {
            maxVals[i] = max(resMat[i]) ;
        }
        int maxVal = max(maxVals);
        return maxVal*maxVal;
    }

    public static int min(int[] arr) {

        int x = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < x) x = arr[i];
        }
        return x;
    }

    public static int max(int[] arr) {

        int x = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > x) x = arr[i];
        }
        return x;
    }
}
