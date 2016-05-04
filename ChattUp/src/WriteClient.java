

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class WriteClient extends Thread {
	private Socket socket;
	public WriteClient(Socket socket){
		this.socket = socket;
	}

	public void run() {
	
//		System.out.println("Connecting to server on port 30000");
//		Socket socket;
		boolean isOnline = true;
		try {
//			socket = new Socket("localhost", 30000);
//			System.out.println("Just connected to " + socket.getInetAddress());
			while (isOnline) {
				Scanner scan = new Scanner(System.in);
				String message = scan.nextLine();
				PrintStream print = new PrintStream(socket.getOutputStream());
				print.println(message);
				if(message.startsWith("Q")){
					isOnline = false;
					break;
				}
			}
			socket.close();

		} catch (IOException e) {

			e.printStackTrace();
		}
	
		
	}

}
