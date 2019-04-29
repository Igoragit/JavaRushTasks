package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Закрепляем Singleton pattern
*/

public class Solution {
    public static void main(String[] args) {

    }

    public static Planet thePlanet;

    static {

        readKeyFromConsoleAndInitPlanet();
    }

    //add static block here - добавьте статический блок тут

    public static void readKeyFromConsoleAndInitPlanet() {
        // implement step #5 here - реализуйте задание №5 тут

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str="";
        try {
             str = reader.readLine();
        }catch (IOException e){

        }

        switch (str){

            case "sun":
                thePlanet =  Sun.getInstance();
                break;
            case "moon":
                thePlanet = Moon.getInstance();
                break;
            case "earth":
                thePlanet = Earth.getInstance();
                break;
            default: thePlanet = null;
            break;
        }

    }
}
