package com.javarush.task.task19.task1904;

import com.sun.org.apache.regexp.internal.RE;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {

    }

    public static class PersonScannerAdapter implements PersonScanner {

        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner scanner){

           this.fileScanner = scanner;
        }

        @Override
        public Person read() throws IOException {
            String[] splittedInfo = fileScanner.nextLine().split(" ");
            Date readedDate = new GregorianCalendar(Integer.parseInt(splittedInfo[5]), Integer.parseInt(splittedInfo[4])-1, Integer.parseInt(splittedInfo[3])).getTime();
            return new Person(splittedInfo[1], splittedInfo[2], splittedInfo[0], readedDate);
        }

        @Override
        public void close() throws IOException {

            fileScanner.close();
        }
    }
}
