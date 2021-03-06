package Client;


	import java.io.IOException;
	import java.io.PrintStream;
	import java.net.Socket;
	import java.util.Scanner;

	public class SendFile extends Thread {
		private Socket socket;

		public SendFile(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			boolean isOnline = true;
			try {
				while (isOnline) {
					Scanner scan = new Scanner(System.in);
					String message = scan.nextLine();
					PrintStream print = new PrintStream(socket.getOutputStream());
					print.println(message);
					if (message.startsWith("Q")) {
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
