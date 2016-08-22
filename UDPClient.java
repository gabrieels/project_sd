package rtt_ud_tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {
	static final int RTT = 100;

	public static void main(String[] args) throws UnknownHostException {
		DatagramSocket sock = null;
		
		byte[] buffReceive = new byte[1];
		int[] size = { 1, 8, 16, 32, 64, 128, 256, 512, 1024 };

		InetAddress ip = InetAddress.getByName("localhost");

		try {
			for (int j = 0; j < size.length; j++) {
				for (int i = 1; i <= 10; i++) {
					byte[] buffSend = new byte[size[j]];
					int count = 0;
					
					System.out.println("Rodada " + i + " Buffer " + buffSend.length);
					while (count < RTT) {
						sock = new DatagramSocket(3000);
						DatagramPacket pack = new DatagramPacket(buffSend, buffSend.length, ip, 6789);
						DatagramPacket reply = new DatagramPacket(buffReceive, buffReceive.length);

						long tempoInicial = System.currentTimeMillis();

						sock.send(pack);
						sock.receive(reply); // bloqueante

						System.out.print(System.currentTimeMillis() - tempoInicial + " - ");
						++count;

						sock.close();
					}
					System.out.println("Enviados: " + count);
				}
				System.out.println();
				
			}

		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (sock != null) {
				sock.close();
			}
		}
	}
}
