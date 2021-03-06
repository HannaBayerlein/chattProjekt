package server; //chattApp
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.FileBox;
import server.FileGetter;
import server.FileSession;
import server.User;

public class TCPServer2 {

	public static ArrayList<Socket> connections = new ArrayList<Socket>();
	public static ArrayList<User> activeUsers = new ArrayList<User>();
	public static ArrayList<Socket> fileConnections = new ArrayList<Socket>(); //ny!

	public static void main(String[] args) {
		MailBox mailBox = new MailBox();
		FileBox fileBox = new FileBox(); //ny!
		ServerSocket server;

		/**
		 * Server connects to port 30000 and waits for clients to accept.
		 */

		try {
			server = new ServerSocket(30000);
			System.out.println("Rätt server!! Waiting for clients on port 30000...");
			MessageGetter messageGetter = new MessageGetter(mailBox,
					activeUsers);
			messageGetter.start();
			FileGetter fileGetter = new FileGetter(fileBox, activeUsers); //ny!
			fileGetter.start(); //ny!

			while (true) {
				Socket socket = server.accept();
				Socket fileSocket = server.accept(); //ny
				connections.add(socket);
				fileConnections.add(fileSocket); // Ny
				System.out.println("Client connected to socket from: "
						+ socket.getLocalAddress().getHostName());

				/**
				 * User chooses its unique nickname for the chat. The nickname
				 * must be unique, otherwise the user must chose a different
				 * nickname.
				 */

				PrintStream print = new PrintStream(socket.getOutputStream());
//				print.println("Välj användarnamn:");

				InputStreamReader in = new InputStreamReader(
						socket.getInputStream());
				BufferedReader buffer = new BufferedReader(in);
				String userName = buffer.readLine();
				User user = new User(socket, fileSocket, userName); //+fileSocket ny!
				//User user = new User(socket, userName); 
				System.out.println("Servern har lagt till i sin databas: "+ user.getNick());

				for (int i = 0; i < activeUsers.size(); i++) {
					User tempUser = activeUsers.get(i);
					while (tempUser.getNick().equals(user.getNick())) {
						print.println("Användarnamnet används redan, välj ett nytt:");
						userName = buffer.readLine();
						user.setNick(userName);
					}
				}

				/**
				 * The new users adds in the active users list. A new session
				 * starts with the new user and the mailbox as parameters.
				 */

				activeUsers.add(user);
				String newUser = "joined the chatt";
				
				StringBuilder sb=new StringBuilder();
				for(User u: activeUsers){
					sb.append("&&&"+u.getNick());
				}
				newUser = newUser+sb.toString();
				//mailBox.add(user,sb.toString());
				System.out.println("TCP2 skapade detta: "+newUser);
				try {
					mailBox.add(user, newUser);
					
					//Skickar ut ny user-lista
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Session session = new Session(mailBox, user, activeUsers);
				FileSession fileSession = new FileSession(fileBox, user, activeUsers);
				session.start();
				fileSession.start();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
