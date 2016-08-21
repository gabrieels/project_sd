import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) throws IOException {

		ServerSocket ss = new ServerSocket(7000);
		Socket s = ss.accept();

		InputStream is = s.getInputStream();
		OutputStream os = s.getOutputStream();
		// byte[] buffReceive = new byte[1];
		int[] size = { 1, 8, 16, 32, 64, 128, 256, 512, 1024 };
		for (int j = 0; j < size.length; j++) {
			for (int i = 1; i <= 10; i++) {

				byte[] buffReceive = new byte[size[j]];

				//System.out.println("Rodada " + i + " - Tamanho do Buffer " + size[j]);
				int count = 0;
				while (count < 100) {
					is.read(buffReceive);
					os.write(buffReceive);

					//System.out.print("Recebido!" + " - ");
					++count;
				}
				System.out.println();
			}
		}

	}
}
