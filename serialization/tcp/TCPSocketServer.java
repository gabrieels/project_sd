package serialization.tcp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import serialization.Student;

public class TCPSocketServer {
	private ServerSocket listenSocket = null;
	private Socket socket = null;
	private OutputStream os = null;
	private InputStream is = null;

	public TCPSocketServer() {

	}

	public void createAndListenSocket() {

		try {
			listenSocket = new ServerSocket(9876);
			socket = listenSocket.accept();

			byte[] incomingData = new byte[1024];
			is = socket.getInputStream();
			is.read(incomingData);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(incomingData);
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

			try {
				Student student = (Student) objectInputStream.readObject();
				System.out.println("Student object received = " + student);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String reply = "Thank you for the message";
			byte[] replyBytea = reply.getBytes();
			os = socket.getOutputStream();
			os.write(replyBytea);

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
		TCPSocketServer server = new TCPSocketServer();
		server.createAndListenSocket();
	}

}
