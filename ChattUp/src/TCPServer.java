import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		TCPServer server = new TCPServer();
		server.run();
	}

	public void run() {

		while (true) {

			try {

				ServerSocket serverSocket = new ServerSocket(30000);
				System.out.println("Waiting for client on port "
						+ serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				InputStreamReader in = new InputStreamReader(
						server.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String message = buffer.readLine();
				System.out.println(message);

				if (message != null) {
					PrintStream print = new PrintStream(
							server.getOutputStream());
					print.println("Hej Felicia");

				}

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}

// }
