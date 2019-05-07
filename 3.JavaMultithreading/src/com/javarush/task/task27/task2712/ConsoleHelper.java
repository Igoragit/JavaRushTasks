package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ConsoleHelper {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        System.out.println(message);
    }

    public static String readString() throws IOException {
       return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishList = new LinkedList<>();

        String allDishes =Dish.allDishesToString();
        writeMessage(allDishes);
        writeMessage("Выберите блюдо");
        String str;

        while (true){
            str = readString();
            if(str.equals("exit")){
                break;
            }
            if(!allDishes.contains(str)){
                writeMessage("такого блюда нет и продолжи формирование заказа. Введиде блюдо.");
            }

            else {
                dishList.add(Dish.valueOf(str));
            }

        }

        return dishList;
    }
}
