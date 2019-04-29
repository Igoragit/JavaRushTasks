package com.javarush.task.task26.task2603;

import java.util.Comparator;

public class Woman  {

    Comparator<Woman> compareByAge = new Comparator<Woman>() {
        public int compare(Woman o1, Woman o2) {
            return 0;
        }
    };

}
