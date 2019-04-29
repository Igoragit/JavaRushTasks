package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected=false;


    public class  SocketThread extends Thread{

        public void run(){



            try {
                java.net.Socket socket = new java.net.Socket(getServerAddress(),getServerPort());
                connection = new Connection(socket);

                    clientHandshake();
                    clientMainLoop();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                notifyConnectionStatusChanged(false);
            }
        }

        protected void processIncomingMessage(String message){
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " присоеденился к чату");
        }

        protected void informAboutDeletingNewUser(String userName){
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected){
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this){
                Client.this.notify();
           }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException{

            while (true){
                Message message = connection.receive();
                if(message.getType()==MessageType.NAME_REQUEST) {
                    connection.send(new Message(MessageType.USER_NAME,Client.this.getUserName()));
                }
                if(message.getType()==MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                }
                if(message.getType()!=MessageType.NAME_REQUEST) throw new IOException("Unexpected MessageType");
            }

        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException{
            while (true){
                Message message = connection.receive();

                if(message.getType()==MessageType.TEXT ||message.getType()==MessageType.USER_ADDED || message.getType()==MessageType.USER_REMOVED){
                    switch (message.getType()){
                        case TEXT:processIncomingMessage(message.getData());
                        case USER_ADDED:informAboutAddingNewUser(message.getData());
                        case USER_REMOVED:informAboutDeletingNewUser(message.getData());
                    }
                } else throw new IOException("Unexpected MessageType");
            }
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    public void run()   {

        Thread thread = getSocketThread();
        thread.setDaemon(true);
        thread.start();

        synchronized (this){
            try {
                wait();
                if(clientConnected){
                    System.out.println("Соединение установлено. Для выхода наберите команду 'exit'.");
                    String str="";
                    while (clientConnected){
                        str = ConsoleHelper.readString();
                        if(str.equals("exit")) break;
                        if(shouldSendTextFromConsole()) sendTextMessage(str);
                    }


                }else {
                    System.out.println("Произошла ошибка во время работы клиента.");
                }


            } catch (InterruptedException e) {
                System.out.println("exeption");

                e.printStackTrace();
            }
        }

    }

    protected String getServerAddress(){

        return ConsoleHelper.readString();
    }

    protected int getServerPort(){

        return ConsoleHelper.readInt();
    }

    protected String getUserName(){
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole(){
        return true;
    }

    protected SocketThread getSocketThread(){

        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT,text));
        } catch (IOException e) {
            clientConnected=false;
            e.printStackTrace();
        }
    }
}
