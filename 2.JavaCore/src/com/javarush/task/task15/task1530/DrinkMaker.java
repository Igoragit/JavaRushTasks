package com.javarush.task.task15.task1530;

/**
 * Created by Гор on 2017-02-10.
 */
public abstract class DrinkMaker {


    abstract void getRightCup();
    abstract void putIngredient();
    abstract void pour();

    void makeDrink(){

        getRightCup();
        putIngredient();
        pour();

    }

}
