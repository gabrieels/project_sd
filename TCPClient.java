import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	static final int RTT = 100;

	public static void main(String[] args) throws IOException {
		// ec2-54-197-8-9.compute-1.amazonaws.com
		Socket s = new Socket("localhost", 7000);

		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();

		int[] size = { 1, 8, 16, 32, 64, 128, 256, 512, 1024 };
		for (int j = 0; j < size.length; j++) {
			for (int i = 1; i <= 10; i++) {

				byte[] buffSend = new byte[size[j]];
				byte[] buffReceive = new byte[size[j]];

				System.out.println("Rodada " + i + " - Tamanho do Buffer " + size[j]);
				int count = 0;
				while (count < RTT) {
					long tempoInicial = System.currentTimeMillis();

					os.write(buffSend);
					is.read(buffReceive);

					System.out.print(System.currentTimeMillis() - tempoInicial + ", ");
					++count;
				}
				System.out.println();
			}
		}

		s.close();
	}
}
