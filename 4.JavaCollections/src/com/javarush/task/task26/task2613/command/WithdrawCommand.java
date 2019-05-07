package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.CurrencyManipulator;
import com.javarush.task.task26.task2613.CurrencyManipulatorFactory;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

class WithdrawCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        String str = ConsoleHelper.askCurrencyCode();
        CurrencyManipulator currencyManipulator =
                CurrencyManipulatorFactory.getManipulatorByCurrencyCode(str);

        String[] count;
        do {
             count = ConsoleHelper.getValidTwoDigits(str);
        }while (!currencyManipulator.isAmountAvailable(Integer.parseInt(count[0]) *
                Integer.parseInt(count[1])));
    }
}
