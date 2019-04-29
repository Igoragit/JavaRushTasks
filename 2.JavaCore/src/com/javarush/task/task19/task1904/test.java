package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Gor_I on 15.03.2017.
 */
public class test {


    public static void main(String[] args) throws IOException {

        File file = new File("G:\\file.txt");
        Scanner scanner = new Scanner(file);


        PersonScanner personScanner = new Solution.PersonScannerAdapter(scanner );
        System.out.println( personScanner.read());

    }
}
