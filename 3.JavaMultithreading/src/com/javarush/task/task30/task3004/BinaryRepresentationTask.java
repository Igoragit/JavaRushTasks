package com.javarush.task.task30.task3004;

import java.util.concurrent.RecursiveTask;

public class BinaryRepresentationTask extends RecursiveTask<String> {

    private int x;
    private StringBuilder res;

    public BinaryRepresentationTask(int x) {
    this.x=x;
    }


    @Override
    protected String compute() {

        int a = x % 2;
        int b = x / 2;
        System.out.println("a=" +a);
        System.out.println("b=" +b);
        System.out.println("res=" +res);
        String res = String.valueOf(a);

        if (b > 0) {
            BinaryRepresentationTask task = new BinaryRepresentationTask(b);
            task.fork();
            return task.join() + res;
        }
        return res;


    }
}
