package com.javarush.task.task15.task1522;

/**
 * Created by Гор on 2017-02-09.
 */
public class Sun implements Planet {

    private static Sun instance;

    private Sun(){

    }


    public static Sun getInstance(){

        if(instance == null)
            synchronized (Sun.class){

                if(instance == null) instance = new Sun();
            }
        return instance;
    }
}
