package rtt_ud_tcp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {

		DatagramSocket sock = null;

		try {
			// reserva uma porta livre para a aplicacao
			sock = new DatagramSocket(6789);
			byte[] buffer = new byte[1];
			
			while (true) {
				
				DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
				sock.receive(receivePacket); // operacao bloqueante

				DatagramPacket reply = new DatagramPacket(buffer, buffer.length, receivePacket.getAddress(),
						receivePacket.getPort());

				sock.send(reply);
				System.out.println("Recebido!");
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
