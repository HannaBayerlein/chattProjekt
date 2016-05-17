package Client2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import GUI.Controller;

public class ReadClient extends Thread {

	private Socket socket;
	private Controller controller;
	public ReadClient(Socket socket, Controller controller) {
		this.socket = socket;
		this.controller=controller;
	}

	public void run() {
		boolean isOnline = true;
		try {

			while (isOnline) {

				InputStreamReader in = new InputStreamReader(
						socket.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String answer = buffer.readLine();
				if (answer.endsWith("Q")) {
					isOnline = false;
				} else {
					System.out.println("hjehejhejehjehallluuuuu");
					System.out.println(answer);
					controller.receive(answer);
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
