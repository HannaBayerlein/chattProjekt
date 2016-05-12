package server;
import java.net.Socket;

public class Message {
	private User user;
	private String message;

	public Message(User user, String message) {
		this.user = user;
		this.message = message;
	}

	public Socket getConnection() {
		return user.getSocket();
	}

	public User getUser() {
		return user;
	}

	public String getMail() {
		return message;
	}

}
