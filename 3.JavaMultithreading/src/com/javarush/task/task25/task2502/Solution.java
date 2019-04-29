package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws IllegalAccessException {
            //init wheels here
            String[] strings = loadWheelNamesFromDB();
            wheels = new ArrayList<>();
            if(strings.length!=4) throw new IllegalAccessException();
            else {
                wheels.add(Wheel.valueOf(strings[0]));
                wheels.add(Wheel.valueOf(strings[1]));
                wheels.add(Wheel.valueOf(strings[2]));
                wheels.add(Wheel.valueOf(strings[3]));
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
