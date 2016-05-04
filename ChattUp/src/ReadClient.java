

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReadClient extends Thread {

	private Socket socket;

	public ReadClient(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		boolean isOnline = true;
		try {

			while (isOnline){

				InputStreamReader in = new InputStreamReader(
						socket.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String answer = buffer.readLine();
				if (answer.endsWith("Q")){
					isOnline = false;
				}else{
					System.out.println(answer);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

// public static void main(String[] args) {
//
// ReadClient client = new ReadClient();
// client.start();
//
// }

// System.out.println("Connecting to server on port 30000");
// Socket socket = new Socket("localhost", 30000);
// System.out.println("Just connected to " +
// socket.getInetAddress());
// if(answer.equals("Välj användarnamn:")){
// print.println("User " + message + " joined the chatt");
// }