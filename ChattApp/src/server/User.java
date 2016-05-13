package server;
import java.net.Socket;

public class User {
	private String nickName;
	private boolean active = false;
	private Socket socket;

	public User(Socket socket, String userName) {
		this.socket = socket;
		nickName = userName;
		active = true;
	}

	public void setNick(String nick) {
		nickName = nick;
	}

	public String getNick() {
		return nickName;
	}

	public boolean isActive() {
		return active;
	}

	public Socket getSocket() {
		return socket;
	}

}
