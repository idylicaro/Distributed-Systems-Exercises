import java.io.IOException;
import java.net.*;

public class ClienteBroadcast {

	public static void main(String[] args) throws IOException {
		//Classe java para trabalhar com multicast ou broadcast
		MulticastSocket mcs = new MulticastSocket(6001);//porta como parametro
		while (true) {

			try {
				byte rec[] = new byte[256];
				DatagramPacket pkg = new DatagramPacket(rec, rec.length);
				mcs.receive(pkg);//recebendo dados enviados via broadcast
				String data = new String(pkg.getData(), 0, pkg.getLength());
				System.out.println("Dados recebidos: " + data);
			}

			catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}
		}
	}
}
