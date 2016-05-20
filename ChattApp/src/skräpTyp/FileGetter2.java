package skr�pTyp;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.net.Socket;
	import java.util.ArrayList;
	import java.util.Iterator;

import server.MailBox;
import server.Message;
import server.User;

public class FileGetter2 extends Thread{



		private MailBox mailBox;
		private ArrayList<User> users;

		public FileGetter2(MailBox mailBox, ArrayList<User> users) {
			this.mailBox = mailBox;
			this.users = users;
		}

		public void run() {

			Message message;
			String mail;
			Socket connection;
			User user;
			try {
				while (true) {
					message = mailBox.getMessage();
					user = message.getUser();
					mail = message.getMail();
					connection = message.getConnection();

					Iterator<User> itr = users.iterator();

					if (mail.startsWith("E:")) {
						String echoMail = "Echo: "
								+ mail.substring(3, mail.length());
						Socket temp = connection;
						write(temp, echoMail);

					} else if (mail.startsWith("P:")) {
						String privateMail = mail.substring(3, mail.length());
						boolean privateMailSent = false;
						
						while (itr.hasNext()) {
							User tempUser = itr.next();
							if (privateMail.startsWith(tempUser.getNick())) {
								privateMail = "Privat: " + privateMail.substring(tempUser
										.getNick().length(), privateMail.length());
								Socket temp = tempUser.getSocket();
								write(temp, user.getNick(), privateMail);
								privateMailSent = true;

							}
						}
						if (!privateMailSent) {
							String exceptionMail = "Den sökta användaren är inte inloggad";
							Socket temp = connection;
							write(temp, exceptionMail);
						}

					} else {

						while (itr.hasNext()) {
							User tempUser = itr.next();
							Socket temp = tempUser.getSocket();
							write(temp, user.getNick(), mail);

						}

					}
				}
			} finally {
			}
		}

		public void write(Socket socket, String userNick, String mail) {

			PrintWriter tempout;

			try {
				tempout = new PrintWriter(socket.getOutputStream());
				tempout.println(userNick + ": " + mail);
				tempout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void write(Socket socket, String exceptionMail) {
			PrintWriter tempout;

			try {
				tempout = new PrintWriter(socket.getOutputStream());
				tempout.println(exceptionMail);
				tempout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

