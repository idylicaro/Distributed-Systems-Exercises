package com.idylicaro;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {
    private final static int PORT = 6000;
    private static ServerSocket serverSocket;
    private Socket client;


    public Server(Socket client) {
        this.client = client;
    }


    public void run() {
        try {
            // try read input client (filename) - Timeout 1 minute
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String filename = "arquivos\\"+ (inFromClient.readLine()); // todo: if timeout 1 minute -> close connection
            // send file
            File file = new File(filename);
            if(file.exists()){
                FileInputStream fileIn = new FileInputStream(file);
                OutputStream socketOut = client.getOutputStream();
                byte[] cbuffer = new byte[1024];
                int bytesRead;
                System.out.println("Sending... : " + filename);
                while ((bytesRead = fileIn.read(cbuffer)) != -1) {
                    socketOut.write(cbuffer, 0, bytesRead);
                    socketOut.flush();
                }
                fileIn.close();
                client.close();
                System.out.println("File sent!");
            }else{
                System.out.println("Can't find file");
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println("Server Socket Running in "
                +serverSocket.getInetAddress() + ":" +
                +serverSocket.getLocalPort());
        while (true) {
            try {
                Socket client = serverSocket.accept();
                System.out.println("======================================");
                System.out.println("Client Accepted");
                System.out.println("HOSTNAME = " + client.getInetAddress().getHostName());
                System.out.println("HOST ADDRESS = " + client.getInetAddress().getHostAddress());
                System.out.println("LOCAL PORT = " + client.getLocalPort());
                System.out.println("CONNECTION PORT = " + client.getPort());
                System.out.println("======================================");
                new Server(client).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
