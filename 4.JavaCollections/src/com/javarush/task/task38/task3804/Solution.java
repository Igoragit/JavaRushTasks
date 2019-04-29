package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class<ExceptionFactory> getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {

     //   System.out.println(getFactoryClass().getException(ExceptionApplicationMessage.SOCKET_IS_CLOSED));
    }
}