package com.javarush.task.task17.task1711;


import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createFemale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws IOException {
        //start here - начни тут
        String[] arguments =args;

        // BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // String[] arguments =reader.readLine().split(" ");

        switch (args[0]) {

            case "-c": {
                synchronized (allPeople) {
                    for (int x = 1; x < arguments.length; x = x + 3) {

                        if (arguments[x + 1].equals("м"))
                            allPeople.add(Person.createMale(arguments[x], calendar(arguments[x + 2])));
                        if (arguments[x + 1].equals("ж"))
                            allPeople.add(Person.createFemale(arguments[x], calendar(arguments[x + 2])));
                        System.out.println(allPeople.size() - 1);
                    }
                }
            }
            break;

            case "-u": {
                synchronized (allPeople) {
                    for (int x = 1; x < arguments.length; x = x + 4) {
                        if (arguments[x + 2].equals("м"))

                            allPeople.set(Integer.parseInt(arguments[x]), Person.createMale(arguments[x + 1], calendar(arguments[x + 3])));
                        if (arguments[x + 2].equals("ж"))
                            allPeople.set(Integer.parseInt(arguments[x]), Person.createFemale(arguments[x + 1], calendar(arguments[x + 3])));

                    }
                }
            }
            break;

            case "-d": {
                synchronized (allPeople) {
                    for (int x = 1; x < arguments.length; x++) {
                        allPeople.get(Integer.parseInt(arguments[x])).setBirthDay(null);
                        allPeople.get(Integer.parseInt(arguments[x])).setName(null);
                        allPeople.get(Integer.parseInt(arguments[x])).setSex(null);
                    }
                }
            }
            break;

            case "-i": {
                synchronized (allPeople) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

                    for (int x = 1; x < arguments.length; x++) {

                        String str = allPeople.get(Integer.parseInt(arguments[x])).getName() + " " + ((allPeople.get(Integer.parseInt(arguments[x])).getSex()).equals(Sex.MALE) ? "м" : "ж") + " " + dateFormat.format(allPeople.get(Integer.parseInt(arguments[x])).getBirthDay());
                        System.out.println(str);

                    }
                }
            }
            break;
        }
    }


    private static Date calendar(String date){

        Calendar cal = Calendar.getInstance();
        String[] BirthDay = date.split("/");
        cal.set(Integer.parseInt(BirthDay[2]),Integer.parseInt(BirthDay[1])-1,Integer.parseInt(BirthDay[0]));

        Date bd = cal.getTime();
        cal.clear();
        return bd;

    }
}
