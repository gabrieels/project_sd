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
		byte[] buffer = new byte[1];
		InetAddress ip = InetAddress.getByName("ec2-54-149-218-127.us-west-2.compute.amazonaws.com");
		System.out.println("Meu IP: " + ip);
		
		try {
			for (int i = 1; i <= 10; i++) {
				System.out.println("Rodada " + i);
				int count = 0;
				while (count < RTT) {
					long tempoInicial = System.currentTimeMillis();
					sock = new DatagramSocket(3000);

					DatagramPacket pack = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 5000);
					sock.send(pack);
					
					System.out.print(System.currentTimeMillis() - tempoInicial + " - ");
					++count;
					
					sock.close();
				}
				System.out.println();
				System.out.println("Enviados: " + count);
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
