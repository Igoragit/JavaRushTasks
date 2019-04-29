package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {


        threads.add( new n1());
        threads.add( new n2());
        threads.add( new n3());
        threads.add( new n4());
        threads.add( new n5());


    }
    public static void main(String[] args) {


    }

    public static class n1  extends Thread{

        @Override
        public void run() {
            while (true){

            }
        }
    }

    public static class n2  extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                System.out.println("InterruptedException");
            }
        }
    }

    public static class n3  extends Thread{

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);

                } catch (InterruptedException e) {
                    //  System.out.println("InterruptedException");
                }
            }
        }
    }

    public static class n4 extends Thread implements Message{

        @Override
        public void run() {
            while (!isInterrupted()){
                try {

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void showWarning() {

            this.interrupt();

        }
    }

    public static class n5  extends Thread{



        int i=0;
        @Override
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String string="";
            ArrayList<String> stringArrayList = new ArrayList<>();

            while (!string.equals("N")) {

                try {
                    string = reader.readLine();
                    if (!Objects.equals(string, "N")) stringArrayList.add(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            for(String str: stringArrayList){

                try {
                    i =i+ Integer.parseInt(str);
                }catch (Exception e){
                    System.out.println(i);
                }
        }

            System.out.println(i);

        }
    }


}