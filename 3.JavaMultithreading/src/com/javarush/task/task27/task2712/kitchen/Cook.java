package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.Observer;

public class Cook extends java.util.Observable implements  Observer {
    private String name;

    public Cook(String name) {
        this.name=name;
        ConsoleHelper.writeMessage(this.name);
    }


    @Override
    public void update(java.util.Observable observable, Object arg) {
        Order order = (Order) arg;
        ConsoleHelper.writeMessage("Start cooking - " + order + ", cooking time " + order.getTotalCookingTime() + "min");
        setChanged();
        notifyObservers(arg);


    }

    @Override
    public String toString() {
        return name;
    }
}
