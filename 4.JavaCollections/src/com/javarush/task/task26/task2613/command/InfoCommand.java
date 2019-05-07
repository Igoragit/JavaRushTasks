package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;


import java.util.Collection;


class InfoCommand implements Command {
    @Override
    public void execute() {

       Collection<CurrencyManipulator> manipulatorCollections =
               CurrencyManipulatorFactory.getAllCurrencyManipulators();

       boolean isMoney = false;
       for (CurrencyManipulator into: manipulatorCollections){
           if(into.getTotalAmount()!=0){
               System.out.println(into.getCurrencyCode() + " - " + into.getTotalAmount());
               isMoney=true;
           }
       }
       if(!isMoney)  System.out.println("No money available.");
       }
}
