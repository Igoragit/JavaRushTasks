package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    static ArrayList<Integer> arrayList = new ArrayList<Integer>();

    public static ByteArrayOutputStream getPassword() {

        countGen();

        StringBuilder stringBuilder = new StringBuilder();
        ArrayList<String> stringArrayList = new ArrayList<>();


        for (int x = 0; x < arrayList.get(0); x++){
            stringArrayList.add(String.valueOf(getRandomNum()));
        }
        for (int x = 0; x < arrayList.get(1); x++) {
            stringArrayList.add(Character.toString(getRandomUpLetter()));
        }
        for (int x = 0; x < arrayList.get(2); x++) {
            stringArrayList.add(Character.toString(getRandomLowLetter()));
        }

        Random r = new Random();
        int nexValMax=8;



        while (stringArrayList.size()>0){

            int index =  r.nextInt(nexValMax);
            stringBuilder.append(stringArrayList.get(index));
            if(nexValMax>0)nexValMax--;
            stringArrayList.remove(index);
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(stringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream;

    }


    public static void countGen(){

          int maxForOne=6;
          int max=8;

        Random r = new Random();
        for(int x=0; x<2; x++){
            int res = r.nextInt(maxForOne);
            if(res==0)res++;
            arrayList.add(res);
            maxForOne = max-res-1;
            max -=res;
        }
        arrayList.add(max);
    }


    public static int getRandomNum (){
        double i = Math.random();
        int x = (int)(i*10);
        return x;
    }

    public static char getRandomUpLetter(){

        Random random = new Random();
        int numUp = 65 + random.nextInt(90 - 65);
        char letter;
        letter = (char)numUp;
        return letter;
    }
    public static char getRandomLowLetter() {

        Random random = new Random();
        int numLow = 97 + random.nextInt(122 - 97);
        char letter;
        letter = (char)numLow;
        return letter;
    }


    }