import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {

		DatagramSocket sock = null;
		int[] size = { 1, 8, 16, 32, 64, 128, 256, 512, 1024 };

		try {
			// reserva uma porta livre para a aplicacao
			sock = new DatagramSocket(6789);
			//byte[] buffReceive = new byte[1];
			byte[] bufferSend = new byte[1];

			while (true) {
				for (int j = 0; j < size.length; j++) {
					byte[] buffReceive = new byte[size[j]];
					
					DatagramPacket receivePacket = new DatagramPacket(buffReceive, buffReceive.length);
					sock.receive(receivePacket); // operacao bloqueante

					DatagramPacket reply = new DatagramPacket(bufferSend, bufferSend.length, receivePacket.getAddress(),
							receivePacket.getPort());

					sock.send(reply);
				}
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
