

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
	private ReadClient rClient;
	private WriteClient wClient;

	public Client() {
		
	}

	public void run() {
		System.out.println("Connecting to server on port 30000");
		Socket socket;

		try {
			socket = new Socket("localhost", 30000);
			System.out.println("Just connected to " + socket.getInetAddress());
			wClient = new WriteClient(socket);
			rClient = new ReadClient(socket);
			wClient.start();
			rClient.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
