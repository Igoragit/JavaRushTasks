package com.javarush.task.task26.task2611;

import java.util.concurrent.ConcurrentHashMap;

public class Producer implements Runnable {


    private ConcurrentHashMap<String, String> map;

    public Producer(java.util.concurrent.ConcurrentHashMap<String, String> map) {
        this.map = map;
    }

    @Override
    public void run() {
        int x=1;
        try {
            while (true){

                map.put(Integer.toString(x), "Some text for " + Integer.toString(x));
                x++;
                Thread.sleep(500);
            }

        } catch (InterruptedException e) {
            System.out.println("["+Thread.currentThread().getName()+"]" + " thread was terminated");
          //  e.printStackTrace();
        }
    }
}
