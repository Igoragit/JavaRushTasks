package com.javarush.task.task21.task2112;



import javax.xml.ws.WebServiceException;

public class FakeConnection implements AutoCloseable {

    public FakeConnection() {
        System.out.println("Creating database connection...");
    }

    public void unsupportedOperation() {
        System.out.println("Operation is not supported yet!");
        throw new RuntimeException("UnsupportedOperation!");
    }

    public void usefulOperation() {
        System.out.println("Executing useful operation.");
    }

    @Override
    public void close() throws WebServiceException {
            System.out.println("Closing database connection...");

    }
}
