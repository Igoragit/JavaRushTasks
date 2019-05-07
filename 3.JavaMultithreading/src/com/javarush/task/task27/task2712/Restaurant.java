package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;


public class Restaurant {
    public static void main(String[] args) throws IOException {


        Cook cook = new Cook("Amigo : )");
        Tablet tablet = new Tablet(5);
        Waiter waiter = new Waiter();



        tablet.addObserver(cook);
        tablet.createOrder();
        cook.addObserver(waiter);
    }
}
