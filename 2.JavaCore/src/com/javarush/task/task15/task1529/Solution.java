package com.javarush.task.task15.task1529;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Осваивание статического блока
*/

public class Solution {
    public static void main(String[] args) {

    }
    
    static {
        //add your code here - добавьте код тут

        reset();


    }

    public static Flyable result;

    public static void reset() {
       String param="";
       BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
       try {
            param = reader.readLine();
       }catch (IOException e){

       }

       if(param.equals("helicopter")) result = new Helicopter();
       else if(param.equals("plane")){

           try {

               int pasangers = Integer.parseInt(reader.readLine());
               result = new Plane(pasangers);

           }catch (IOException e){



           }
       }

       try {
           reader.close();
       }catch (IOException e){

       }
        //add your code here - добавьте код тут
    }
}
