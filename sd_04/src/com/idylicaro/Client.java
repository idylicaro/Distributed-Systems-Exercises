package com.idylicaro;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        try {

            System.out.println("Try connection!");
            Socket clientSocket = new Socket("127.0.0.1", 6000);
            System.out.println("Connection established");

            String filename = "index.html";
            DataOutputStream outToServer = // send to server
                    new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes( filename + '\n');

            // creating file to receive server output
            FileOutputStream fileOut = new FileOutputStream("C_" + filename);

            // Creating transfer channel
            InputStream socketIn = clientSocket.getInputStream();

            // Read file receive by socket and write in "arquivos" folder
            System.out.println("Receiving file: " + filename);

            byte[] cbuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = socketIn.read(cbuffer)) != -1) {
                fileOut.write(cbuffer, 0, bytesRead);
            }
            fileOut.close();

            clientSocket.close();
            System.out.println("Received File: arquivos/"+"C_"+filename);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }
}
