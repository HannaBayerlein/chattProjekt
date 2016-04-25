import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MessageGetter extends Thread {
	private MailBox mailBox;

	public MessageGetter(MailBox mailBox) {
		this.mailBox = mailBox;
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
				

				// mailBox.writeTo(user.getNick(), message);
				if (mail.startsWith("E:")) {
					mail = user.getNick() + ": " + mail.substring(3, mail.length());
					Socket temp = connection;
					PrintWriter tempout;
					try {
						tempout = new PrintWriter(temp.getOutputStream());
						tempout.println(mail);
						tempout.flush();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					for (int i = 0; i < TCPServer2.connections.size(); i++) {
						// TCPServer2.users.get(i);
						// temp.getSocket == messageOfUser.getSocket

						// else if(connection != TCPServer2.connections.get(i)){
						Socket temp = TCPServer2.connections.get(i);
						PrintWriter tempout;
						try {
							tempout = new PrintWriter(temp.getOutputStream());
							mail = user.getNick() + ": " + mail;
							tempout.println(mail);
							tempout.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					// System.out.println("Message sent to:"
					// + temp.getLocalAddress().getHostName());
					// }
				}

			}

		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {

		}
	}

}
