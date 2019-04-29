package com.javarush.task.task20.task2017;


import java.io.*;

/* 
Десериализация
*/
public class Solution implements Serializable  {
    public A getOriginalObject(ObjectInputStream objectStream) throws IOException {

        Object object;
        try {

            object = objectStream.readObject();

            if(object instanceof B) return (B)object;
            else if(object instanceof A) return (A)object;
            else return null;

        } catch (Exception e) {
            return null;
        }

    }

    public class A implements Serializable {
    }

    public class B extends A {
        public B() {
            System.out.println("inside B");
        }
    }

    public static void main(String[] args) throws IOException {

        File fl = new File("G:/file.txt");
        InputStream inputStream = new FileInputStream(fl);
        ObjectInputStream inputStreamOb = new ObjectInputStream(inputStream);

        Solution sl = new Solution();
        sl.getOriginalObject(inputStreamOb);

    }
}
