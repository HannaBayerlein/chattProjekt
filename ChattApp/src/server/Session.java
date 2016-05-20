package server;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Session extends Thread {

	private Scanner input;
	private String message = "";
	private MailBox mailBox;
	private User user;
	private ArrayList<User> activeUsers;

	public Session(MailBox mailBox, User user, ArrayList<User> activeUsers) {
		this.mailBox = mailBox;
		this.user = user;
		this.activeUsers = activeUsers;
	}

	public void run() {

		try {

			input = new Scanner(user.getSocket().getInputStream());
			while (true) {

				message = input.nextLine();

				try {
					if (activeUsers.contains(user)) {
						if (message.startsWith("N:")) {
							String oldName= user.getNick();
							String changeNick = message.substring(3,
									message.length());
							user.setNick(changeNick);
							
							StringBuilder sb=new StringBuilder();
							sb.append("Changed name from "+oldName);
							for(User u: activeUsers){
								sb.append("&&&"+u.getNick());
							}
							String updateUserList = sb.toString();
							mailBox.add(user, updateUserList);

						} else if (message.startsWith("Q")) {
							User tempUser = user;
							activeUsers.remove(user);
							String exitMessage = "valde att lämna chatten";
							StringBuilder sb=new StringBuilder();
							sb.append(exitMessage);
							for(User u: activeUsers){
								sb.append("&&&"+u.getNick());
							}
							String exitMessageUpdateUsers = sb.toString();
							
							mailBox.add(tempUser, exitMessageUpdateUsers);

						} else {
							mailBox.add(user, message);
						}
					}

				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
