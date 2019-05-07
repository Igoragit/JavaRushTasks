package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.javarush.task.task26.task2613.Operation.*;

public class ConsoleHelper {

    private static BufferedReader bis = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        String input = "";
        try {
            input = bis.readLine();
            if (input.equalsIgnoreCase("exit")) {
                throw new InterruptOperationException();
            }
        } catch (IOException ignor) {}

        return input;
    }

    public static Operation askOperation() throws InterruptOperationException {
        writeMessage("Insert operation");
       // Operation operations;
        try {
          Operation  operations = getAllowableOperationByOrdinal(Integer.parseInt(readString()));
          return operations;
        } catch (IllegalArgumentException e){

        }
        return askOperation();
    }

    public static String askCurrencyCode() throws InterruptOperationException {

        writeMessage("Insert Currency Code");
        String cc = readString();
        if (cc != null && cc.length() == 3) {
            return cc.toUpperCase();
        }
        writeMessage("Data is not correct");
        return askCurrencyCode();
    }

    public static String[] getValidTwoDigits(String currencyCode) throws InterruptOperationException {

        writeMessage("Write two pos. integer nums. First is par and second is count");
        String ins =  readString();

        try  {
            String[] parCount = ins.split(" ");
            if (parCount.length == 2 ) {
                double var1 = Double.parseDouble(parCount[0]);
                double var2 = Double.parseDouble(parCount[1]);
                if (var1 % 1 == 0 && var2 % 1 == 0) {
                    return parCount;
                }
            }
        }catch (Exception e){
            writeMessage("Data is not correct");
          return   getValidTwoDigits(currencyCode);
        }

        writeMessage("Data is not correct");
        return getValidTwoDigits(currencyCode);
    }

}
