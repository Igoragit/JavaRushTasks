package com.javarush.task.task27.task2712.kitchen;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int i) {
        this.duration = i;
    }

    public static String allDishesToString(){

        String dishes=Fish.toString()+" " +Steak.toString()+" "+Soup.toString()+" "+Juice.toString()+" "+Water.toString()+" ";
        return dishes;
    }


}
