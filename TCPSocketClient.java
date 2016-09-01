package serialization;

import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TCPSocketClient {
	private Socket socket = null;
	private InputStream is = null;
	private OutputStream os = null;

	public TCPSocketClient() {

	}

	public void createAndListenSocket() {

		try {
			socket = new Socket("ec2-52-87-239-240.compute-1.amazonaws.com", 9876);
			Student student = new Student(1, "Bijoy", "Kerala");
			byte[] incomingData = new byte[1024];

			// vertor de bytes a ser preenchido
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			// preparar vetor de bytes
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(arrayOutputStream);
			// Serializa o objeto Student
			objectOutputStream.writeObject(student);

			byte[] data = arrayOutputStream.toByteArray();

			os = socket.getOutputStream();
			os.write(data);

			System.out.println("Message sent from client");
			is = socket.getInputStream();

			is.read(incomingData);
			String response = new String(incomingData);

			System.out.println("Response from server:" + response);

			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TCPSocketClient client = new TCPSocketClient();
		client.createAndListenSocket();
	}

}
