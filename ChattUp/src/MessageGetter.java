import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class MessageGetter extends Thread {
	private MailBox mailBox;
	private ArrayList<User> users;

	public MessageGetter(MailBox mailBox, ArrayList<User> users) {
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

				if (mail.startsWith("E:")) {
					mail = user.getNick() + ": "
							+ mail.substring(3, mail.length());
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

					Iterator<User> itr = users.iterator();
					while (itr.hasNext()) {
						User tempUser = itr.next();
						Socket temp = tempUser.getSocket();

//						System.out.println("Skickar till: "
//								+ tempUser.getNick());
						PrintWriter tempout;
						
						try {

							tempout = new PrintWriter(temp.getOutputStream());
							if(user.getNick().equals(null)){
								tempout.println("Användaren har valt att lämna chatten");
							}else{
							tempout.println(user.getNick() + ": " + mail);
							}
							//System.out.println(mail + "MessageGetter");
							tempout.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

					// for (int i = 0; i < TCPServer2.connections.size(); i++) {
					// Socket temp = TCPServer2.connections.get(i);
					// //PrintWriter tempout;
					// try {
					// ObjectOutputStream oos = new
					// ObjectOutputStream(temp.getOutputStream());
					// //tempout = new PrintWriter(temp.getOutputStream());
					// oos.writeObject(mail);
					// //mail = user.getNick() + ": " + mail;
					// //tempout.println(mail);

				}
			}
		} finally {
		}
	}

}
