package com.javarush.task.task26.task2601;


import java.util.Arrays;
import java.util.Comparator;

/*
Почитать в инете про медиану выборки
*/
public class Solution {

    public static void main(String[] args) {


    }

    public static Integer[] sort(Integer[] array) {

        double median;
        Arrays.sort(array);
        int mid = array.length/2;
        if(array.length%2 != 0) median=array[mid];
        else median = (array[mid-1] + array[mid])/2.0;


        Comparator<Integer> comp = new Comparator<Integer>()
        {
            @Override
            public int compare(Integer o1, Integer o2)
            {
                int result = (int) (Math.abs(o1 - median) - Math.abs(o2 - median));
                return result == 0 ? o1 - o2 : result;
            }
        };

        Arrays.sort(array, comp);

        return array;
    }
}
