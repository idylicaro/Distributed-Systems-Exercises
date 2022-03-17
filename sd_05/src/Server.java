package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Server {
    public static Clock clock;
    public static DatagramSocket socket;
    static boolean isPrincipal;
    static String name;
    public static int countClients = 0;

    public static void main(String[] args) throws IOException {
        Random generator = new Random();
        Server.isPrincipal = true;
        Server.name = "Server " + generator.nextInt(5);
        Server.clock = new Clock();
        Server.clock.serverName = Server.name;
        Server.socket = new DatagramSocket();
        Server.clock.start();
        
        if (isPrincipal)
        {
        	
        	System.out.println("Entrou no principal");
        	Scanner sc = new Scanner(System.in);
        	System.out.println("Digite algum caracter e aperte enter para sincronizar!");
        	sc.next();
        	sc.close();
        	
            // enviar broadcast para todos os nos contendo o tempo do servidor tempo
            try {
                byte[] b = Integer.toString(Server.clock.time).getBytes();
                //Definindo o endere�o de envio do pacote neste caso o endere�o de broadcast
                InetAddress addr = InetAddress.getByName("127.0.0.1");
                DatagramPacket pkg = new DatagramPacket(b, b.length, addr,6000);
                socket.send(pkg);//enviando pacote broadcast
                socket.close();
                System.out.println("Send time!");
            }
            catch (Exception e) {
                System.out.println("Nao foi possivel enviar a mensagem");
            }
            //aguardar receber todas as respostas dos clientes
            ArrayList<DatagramPacket> clients = new ArrayList<DatagramPacket>();
            //settimeout  5s
            // loop infinito
            //somatorio dos tempos enviado nas respostas
            //guarda no arraylist a referen�a dos datagrama que recebeu
            try {
            Server.socket.setSoTimeout(5000);
            while(true) {
                byte[] readStore = new byte[256];
                DatagramPacket pkg = new DatagramPacket(readStore, readStore.length);
                Server.socket.receive(pkg);//recebendo dados enviados via broadcast
            	clients.add(pkg);
            }
            }catch(IOException e) {
            	System.out.println("Recebeu todos");
            }

            //calcula o tempo medio
            int somatorio = 0;
            
            for(DatagramPacket dp: clients) {
            	int diff = Integer. parseInt(new String(dp.getData(), 0, dp.getLength()));
            	somatorio+= diff;
            }
            

            int media = somatorio / (clients.size() + 1);
            
            
            for(DatagramPacket dp: clients) {
            	int diff = Integer. parseInt(new String(dp.getData(), 0, dp.getLength()));
            	System.out.println(diff);
            	int result =  (diff - media) * -1;
            	System.out.println("Result:" + result);
            	try {
                    byte[] b = Integer.toString(result).getBytes();
                    DatagramPacket pkg = new DatagramPacket(b, b.length, dp.getAddress(),dp.getPort());
                    DatagramSocket ds = new DatagramSocket();
                    ds.send(pkg);//enviando pacote broadcast
                    ds.close();
                }
                catch (Exception e) {
                    System.out.println("Nao foi possivel enviar a mensagem");
                }
            	
            }
            
            Server.clock.time += media;

        }




    }
}
