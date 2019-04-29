package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException {
        System.out.println(new Solution(4));

        File file = new File("G:/file.txt");
        InputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = new FileOutputStream(file);

        Solution savedObject = new Solution(4);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedObject);


        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            Solution loaded = (Solution) objectInputStream.readObject();

            if (Objects.equals(savedObject.string, loaded.string)) {
                System.out.println("loaded");
            }
        }catch (ClassNotFoundException e){

        }




    }

    transient private final String pattern = "dd MMMM yyyy, EEEE";
    transient private Date currentDate;
    transient private int temperature;
    String string;

    public Solution(){

    }

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
