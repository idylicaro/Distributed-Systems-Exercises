package com.idylicaro;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {

            System.out.println("Try connection!");
            Socket clientSocket = new Socket("127.0.0.1", 6000);
            System.out.println("Connection established");

            System.out.print("Enter the file name: ");
            Scanner scanner = new Scanner(System. in);
            String filename = scanner. nextLine();

            //String filename = "index.html";
            DataOutputStream outToServer = // send to server
                    new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes( filename + '\n');

            if (clientSocket.isConnected()){

                // creating file to receive server output
                FileOutputStream fileOut = new FileOutputStream("C_" + filename);

                // Creating transfer channel
                InputStream socketIn = clientSocket.getInputStream(); // if timeout 1 minute -> close connection

                // Read file receive by socket and write in "arquivos" folder
                System.out.println("Receiving file: " + filename);
                System.out.println("======================================");

                byte[] cbuffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = socketIn.read(cbuffer)) != -1) {
                    System.out.println("[Received]:\n" + new String(cbuffer, "UTF-8"));
                    fileOut.write(cbuffer, 0, bytesRead);
                }
                System.out.println("======================================");
                fileOut.close();

                clientSocket.close();
                System.out.println("Received File: arquivos/"+"C_"+filename);
            }else{
                System.out.println("Socket connection failed or file not found");
                clientSocket.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
