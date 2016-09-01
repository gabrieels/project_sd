package rmi;

import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;

public class TCPSocketClient {
	private Socket socket = null;
	private InputStream is = null;

	public TCPSocketClient() {
		
	}

	public void createAndListenSocket() {
		
		try {
			socket = new Socket("localhost", 9876);	
			Student student = new Student(1, "Bijoy", "Kerala");
			
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			os.writeObject(student);
			
			System.out.println("Message sent from client");
			is = socket.getInputStream();
			
			byte[] data = new byte[1024];
			is.read(data);
			String response = new String(data);
			
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
