package server;
import java.net.Socket;

public class User {
	private String nickName;
	private boolean active = false;
	private Socket socket;
	private Socket fileSocket; //ny

	public User(Socket socket, Socket fileSocket, String userName) {
		this.socket = socket;
		this.fileSocket = fileSocket;//ny
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
	public Socket getFileSocket() { //ny metod
		return fileSocket;
	}

}
