package com.javarush.task.task20.task2022;

import java.io.*;

/* 
Переопределение сериализации в потоке
*/
public class Solution implements Serializable, AutoCloseable {
    transient  private FileOutputStream stream;
    private String filename;

    public Solution(String fileName) throws FileNotFoundException {
        this.stream = new FileOutputStream(fileName);
        this.filename=fileName;
    }

    public Solution(){

    }

    public void writeObject(String string) throws IOException {
        stream.write(string.getBytes());
        stream.write("\n".getBytes());
        stream.flush();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(this.filename);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

       // filename = (String) in.readObject();
        this.stream = new FileOutputStream(this.filename, true);
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing everything!");
        stream.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Solution so= new Solution("G:/file.txt");
        so.writeObject("here is some data");

        File file = new File("G:/file.txt");
        OutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        so.writeObject(objectOutputStream);


        InputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        Solution loaded = new Solution();
        loaded.readObject(objectInputStream);

        loaded.writeObject("new data");

    }
}
