package com.javarush.task.task38.task3804;

public  class ExceptionFactory {

    public static Throwable getException(Enum e ){
        if(e!=null){
            String original =e.name().replaceAll("_"," ");
            if(e instanceof ExceptionApplicationMessage){
                return new Exception(original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase());
            }
            if(e instanceof ExceptionDBMessage){
                return new RuntimeException(original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase());
            }
            if(e instanceof ExceptionUserMessage){
                return new Error(original.substring(0, 1).toUpperCase() + original.substring(1).toLowerCase());
            } else{
                return new IllegalArgumentException();
            }
        }
        else return new IllegalArgumentException();
    }
}
