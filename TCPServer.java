package rtt_ud_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(7000);
		Socket s = ss.accept();

		InputStream is = s.getInputStream();// canal de entrada
		OutputStream os = s.getOutputStream();// canal de saida
		byte[] buffReceive = new byte[1];

		for (int i = 1; i <= 10; i++) {
			System.out.println("Rodada " + i);
			int count = 0;
			while (count < 100) {
				is.read(buffReceive);
				os.write(buffReceive);

				System.out.print("Recebido!" + " - ");
				++count;
			}
			System.out.println();
		}
	}
}
