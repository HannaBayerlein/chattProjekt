import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Session extends Thread {

	private Scanner input;
	private PrintWriter out;
	String message = "";
	private MailBox mailBox;
	private User user;

	public Session(MailBox mailBox, User user) {
		this.mailBox = mailBox;
		this.user = user;
	}

	public void run() {

		try {

			input = new Scanner(user.getSocket().getInputStream());
			// out = new PrintWriter(user.getSocket().getOutputStream());
			while (true) {
				// if (!input.hasNext()) {
				// return;
				// }

				message = input.nextLine();
				// System.out.println("Session meddelande ");

				// System.out.println(user.getNick() + ": " + message);

				try {
					if (TCPServer2.users.contains(user)) {
						if (message.startsWith("N:")) {
							message = message.substring(3, message.length());
							user.setNick(message);

						} else if (message.startsWith("Q")) {

							mailBox.add(user,
									"Användaren valde att lämna chatten");
							// user.getSocket().close();
							TCPServer2.users.remove(user);

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
