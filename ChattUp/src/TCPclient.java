import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPclient {

	public static void main(String[] args) {

		TCPclient client = new TCPclient();
		client.run();

	}

	public void run() {

		try {
			System.out.println("Connecting to server on port 30000");

			Socket socket = new Socket("localhost", 30000);
			Scanner scan = new Scanner(System.in);
			String message = scan.next();
			PrintStream print = new PrintStream(socket.getOutputStream());
			print.println(message);

			System.out.println("Just connected to " + socket.getInetAddress());

			InputStreamReader in = new InputStreamReader(
					socket.getInputStream());
			BufferedReader buffer = new BufferedReader(in);

			String serverAnswer = buffer.readLine();
			System.out.println(serverAnswer);

			socket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
