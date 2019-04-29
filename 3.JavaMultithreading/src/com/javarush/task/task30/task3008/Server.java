package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String,Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {


       // ConsoleHelper.writeMessage("Введите порт сервера: ");
        int serverPort = ConsoleHelper.readInt();
        ServerSocket serverSocket = null;

        try {
            serverSocket=new ServerSocket(serverPort);
            ConsoleHelper.writeMessage("Сервер запущен");

            while (true) {
                Socket socket = serverSocket.accept();
                if (socket != null) {
                    Handler handler = new Handler(socket);
                    handler.start();
                }
            }
        }catch (Exception e){
            ConsoleHelper.writeMessage("Ошибка закрытия серверного сокета");
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {

            }
        }

    }

    static void sendBroadcastMessage(Message message){

        for(Connection in: connectionMap.values()){
            try {
                in.send(message);
            }catch (IOException e){
                System.out.println("Сообщение не отправлено");
            }
        }
    }


    private static class Handler extends Thread {

        private Socket socket;

        public Handler (Socket socket){
            super();
            this.socket = socket;
        }

        @Override
        public void run() {
           // super.run();
            String userName = null;
            System.out.println(socket.getRemoteSocketAddress());
            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED,userName));
                sendListOfUsers(connection,userName);
                serverMainLoop(connection,userName);


            } catch (IOException e) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
               if(userName!=null){
                   connectionMap.remove(userName);
                   sendBroadcastMessage(new Message(MessageType.USER_REMOVED,userName));
               }
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{

            while (true){
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message userName = connection.receive();

                if (userName.getType() == MessageType.USER_NAME) {
                    if (!userName.getData().isEmpty()) {
                        if (!connectionMap.containsKey(userName.getData())) {
                            connectionMap.put(userName.getData(), connection);
                            connection.send(new Message(MessageType.NAME_ACCEPTED));
                            return userName.getData();
                        }
                    }
                }
            }
        }

       private void sendListOfUsers(Connection connection, String userName) throws IOException{

            for(String in: connectionMap.keySet()) {
                if (!userName.equals(in)) {
                    connection.send(new Message(MessageType.USER_ADDED, in));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            Message message;
            while (true) {
                message = connection.receive();
                if (message.getType()==(MessageType.TEXT)) {
                    String str = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, str));
                } else ConsoleHelper.writeMessage("Error");
            }
        }
    }
}
