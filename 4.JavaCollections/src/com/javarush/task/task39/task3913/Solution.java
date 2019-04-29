package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
        LogParser logParser = new LogParser(Paths.get("c:/logs/"));

    /*   System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getNumberOfUniqueIPs(null, null));*/

        System.out.println(logParser.execute("get ip"));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get date for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get ip for event = \"SOLVE_TASK\" and date between \"11.12.2007 0:00:00\" and \"03.01.2030 23:59:59\""));
        System.out.println(logParser.execute("get status for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2007 0:00:00\" and \"03.01.2030 23:59:59\""));
        System.out.println(logParser.execute("get event for date = \"11.12.2007 0:00:00\" and date between \"11.12.2008 0:00:00\" and \"03.01.2015 23:59:59\""));
        System.out.println(logParser.execute("get event for status = \"OK\" and date between \"11.12.2012 0:00:00\" and \"03.01.2015 23:59:59\""));
        System.out.println(logParser.execute("get event for ip = \"127.0.0.1\""));
        System.out.println(logParser.execute("get user for status = \"OK\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get ip for date = \"05.01.2021 20:22:55\" and date between \"11.12.2013 0:00:00\" and \"03.01.2024 23:59:59\""));
        System.out.println(logParser.execute("get ip for event = \"LOGIN\" and date between \"11.12.2007 0:00:00\" and \"03.01.2030 23:59:59\""));
        System.out.println(logParser.execute("get ip for user = \"Vasya Pupkin\""));
        System.out.println("FOR ++++ EVENT+++ LOGIN");
        System.out.println(logParser.execute("get ip for event = \"LOGIN\""));
        System.out.println("FOR ++++ Vasya Pupkin+++");
        System.out.println(logParser.execute("get date for user = \"Vasya Pupkin\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get date for ip = \"720.120.120.122\" "));
    }
}