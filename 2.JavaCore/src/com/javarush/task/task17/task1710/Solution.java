package com.javarush.task.task17.task1710;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/*
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) throws IOException {
        //start here - начни тут

        String[] arguments =args;
      //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        // String[] arguments =reader.readLine().split(" ");


        if(arguments[0].equals("-c")){
            Calendar cal = Calendar.getInstance();
            String[] BirthDay = arguments[3].split("/");
            cal.set(Integer.parseInt(BirthDay[2]),Integer.parseInt(BirthDay[1])-1,Integer.parseInt(BirthDay[0]));

            Date bd = cal.getTime();

            if(arguments[2].equals("м"))allPeople.add(Person.createMale(arguments[1],bd));
            if(arguments[2].equals("ж"))allPeople.add(Person.createFemale(arguments[1],bd));

            System.out.println(allPeople.size()-1);
        }

        if(arguments[0].equals("-u")) {
            Calendar cal = Calendar.getInstance();
            String[] BirthDay = arguments[4].split("/");
            cal.set(Integer.parseInt(BirthDay[2]),Integer.parseInt(BirthDay[1])-1,Integer.parseInt(BirthDay[0]));

            Date bd = cal.getTime();

            if (arguments[3].equals("м"))
                allPeople.set(Integer.parseInt(arguments[1]), Person.createMale(arguments[2], bd));
            if (arguments[3].equals("ж"))
                allPeople.set(Integer.parseInt(arguments[1]), Person.createFemale(arguments[2], bd));
        }

            if(arguments[0].equals("-d")){

                allPeople.get(Integer.parseInt(arguments[1])).setBirthDay(null);
                allPeople.get(Integer.parseInt(arguments[1])).setName(null);
                allPeople.get(Integer.parseInt(arguments[1])).setSex(null);

            }

            if(arguments[0].equals("-i")){
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                String maleFamel = (allPeople.get(Integer.parseInt(arguments[1])).getSex()).equals("MALE")? "ж":"м";
                String str =  allPeople.get(Integer.parseInt(arguments[1])).getName()+" " +  maleFamel  + " " + dateFormat.format(allPeople.get(Integer.parseInt(arguments[1])).getBirthDay());
                System.out.println(str);
            }
        }
    }

