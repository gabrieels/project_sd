package rtt_ud_tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	static final int RTT = 100;

	public static void main(String[] args) throws IOException {
		Socket s = new Socket("localhost", 7000);

		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();

		byte[] buffSend = new byte[1];
		byte[] buffReceive = new byte[1];
		
		for (int i = 1; i <= 10; i++) {
			System.out.println("Rodada " + i);
			int count = 0;
			while (count < RTT) {
				long tempoInicial = System.currentTimeMillis();
				
				os.write(buffSend);
				is.read(buffReceive);
				
				System.out.print(System.currentTimeMillis() - tempoInicial + " - ");
				++count;
			}
			System.out.println();
		}
		
		s.close();
	}
}
