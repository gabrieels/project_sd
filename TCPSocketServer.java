import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPSocketServer {
	private ServerSocket listenSocket = null;
	private Socket socket = null;
	private OutputStream os = null;

	public TCPSocketServer() {

	}

	public void createAndListenSocket() {

		try {
			listenSocket = new ServerSocket(9876);
			socket = listenSocket.accept();
			os = socket.getOutputStream();

			ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

			try {
				Student student = (Student) inputStream.readObject();
				System.out.println("Student object received = " + student);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			String reply = "Thank you for the message";
			byte[] replyBytea = reply.getBytes();
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
