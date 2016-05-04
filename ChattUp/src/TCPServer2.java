import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TCPServer2 {

	public static ArrayList<Socket> connections = new ArrayList<Socket>();
	public static ArrayList<User> users = new ArrayList<User>();

	public static void main(String[] args) {
		MailBox mailBox = new MailBox();
		ServerSocket server;

			try {
				server = new ServerSocket(30000);
				System.out.println("Waiting for clients on port 30000...");
				MessageGetter messageGetter = new MessageGetter(mailBox, users);
				messageGetter.start();
				
				while (true) {
					Socket socket = server.accept();
					connections.add(socket);
					System.out.println("Client connected to socket from:"
							+ socket.getLocalAddress().getHostName());
					
					PrintStream print = new PrintStream(socket.getOutputStream());
					print.println("Välj användarnamn:");
			//		System.out.println("Välj användarnamn:");
					
					InputStreamReader in = new InputStreamReader(socket.getInputStream());
					BufferedReader buffer = new BufferedReader(in);
					String userName = buffer.readLine();
//					Scanner scan = new Scanner(System.in);
//					String userName = scan.nextLine();
					
					User user = new User(socket, userName);
					users.add(user);
					print.println("User " + user.getNick() + " joined the chatt");
					Session session = new Session(mailBox, user);
					session.start();
					
//		try {
//			server = new ServerSocket(30000);
//			System.out.println("Waiting for clients on port 30000...");
//			MessageGetter messageGetter = new MessageGetter(mailBox, users);
//			messageGetter.start();
//
//			while (true) {
//				Socket socket = server.accept();
//				connections.add(socket);
////				System.out.println("Client connected to socket from:" + socket.getLocalAddress().getHostName());
//				PrintStream print = new PrintStream(socket.getOutputStream());
//				print.println("Välj användarnamn: ");
//				InputStreamReader in = new InputStreamReader(socket.getInputStream());
//				BufferedReader buffer = new BufferedReader(in);
//				String userName = buffer.readLine();
//
//				User user = new User(socket, userName);
//				users.add(user);
//				System.out.println("User " + user.getNick()
//						+ " joined the chatt");
//				Session session = new Session(mailBox, user);
//				session.start();
//
//			}
				}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
