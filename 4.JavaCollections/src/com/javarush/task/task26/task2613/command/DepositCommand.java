package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;


class DepositCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String currency =  ConsoleHelper.askCurrencyCode();
        String[] TD =  ConsoleHelper.getValidTwoDigits(currency);
        CurrencyManipulatorFactory.getManipulatorByCurrencyCode(currency).
                addAmount(Integer.parseInt(TD[0]),Integer.parseInt(TD[1]));
       }
}
