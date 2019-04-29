package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

    private Thread thread;

    @Override
    public void run() {

        String threadName = thread.currentThread().getName();
      //  boolean ch=true;
        try {
            System.out.println(threadName);
            while (true){
                Thread.sleep(100);
                System.out.println(threadName);
            }

        } catch (InterruptedException e) {
            //e.printStackTrace();
        }

    }

    @Override
    public void start(String threadName) {

        this.thread = new Thread(this,threadName);
        this.thread.start();
    }

    @Override
    public void stop() {
        this.thread.interrupt();
    }
}
