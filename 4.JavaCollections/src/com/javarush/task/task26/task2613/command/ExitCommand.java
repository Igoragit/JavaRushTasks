package com.javarush.task.task26.task2613.command;

import com.javarush.task.task26.task2613.ConsoleHelper;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;


class ExitCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {

        ConsoleHelper.writeMessage("exit Y/N");
        String ask = ConsoleHelper.readString();
        if(ask.equals("y")) {
            ConsoleHelper.writeMessage("Good by");

        }/*if(!ask.equals("N") && !ask.equals("Y")){

        }
*/

    }
}
