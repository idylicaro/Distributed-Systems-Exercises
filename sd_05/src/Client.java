package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import java.util.Random;

public class Client {
    public static Clock clock;
    public static DatagramSocket socket;
    static boolean isPrincipal;
    static String name;

    public static void main(String[] args) throws IOException {
        Random generator = new Random();
        Server.socket = new DatagramSocket(6000);
        Client.isPrincipal = false;
        Client.name = "Server " + generator.nextInt(5);
        Client.clock = new Clock();
        Client.clock.serverName = Client.name;
        Client.clock.start();
        Server.countClients+=1;

        if (!isPrincipal)
        {
        	System.out.println("Entrou no secundario");
        	
        		
            byte[] readStore = new byte[256];
            DatagramPacket pkg = new DatagramPacket(readStore, readStore.length);
            int diff = 0;
            int mainClock = 0;
            try {
            Client.socket.receive(pkg);
            mainClock = Integer. parseInt(new String(pkg.getData(), 0, pkg.getLength()));
            diff = Client.clock.time - mainClock;
            System.out.println("Diff: " + diff);
        	}catch (Exception ex) {
                System.out.println(ex);
            }
            
            DatagramSocket ds = new DatagramSocket();;
            try {
                byte[] b = Integer.toString(diff).getBytes();

                pkg = new DatagramPacket(b, b.length, pkg.getAddress(),pkg.getPort());
                ds.send(pkg);
                System.out.println("Send Back time");
            }
            catch (Exception e) {
                System.out.println("Nao foi possivel enviar a mensagem");
            }
            


            readStore = new byte[256];
            pkg = new DatagramPacket(readStore, readStore.length);
            ds.receive(pkg);
            mainClock = Integer. parseInt(new String(pkg.getData(), 0, pkg.getLength()));


            Client.clock.time+= mainClock;
            System.out.println("MainClock" + mainClock);
            Client.socket.close();
            ds.close();
        }




    }
}
