package skr�pTyp;

	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Scanner;

import server.MailBox;
import server.User;
public class FileSession2 extends Thread{

		private Scanner input;
		private String message = "";
		private MailBox mailBox;
		private User user;
		private ArrayList<User> activeUsers;

		public FileSession2(MailBox mailBox, User user, ArrayList<User> activeUsers) {
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
								String changeNick = message.substring(3,
										message.length());
								user.setNick(changeNick);

							} else if (message.startsWith("Q")) {
								String exitMessage = "valde att lämna chatten";
								mailBox.add(user, exitMessage);
								activeUsers.remove(user);

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

