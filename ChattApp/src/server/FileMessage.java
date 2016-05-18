package server;

import java.net.Socket;

public class FileMessage {
	private User user;
	private String fileName;
	private byte[] fileMessage;

	public FileMessage(User user, String fileName, byte[] fileMessage) {
		this.user = user;
		this.fileName=fileName;
		this.fileMessage = fileMessage;
	}

//	public Socket getConnection() {
//		return user.getFileSocket();
//	}

	public User getUser() {
		return user;
	}

	public byte[] getFile() {
		return fileMessage;
	}

	public String getFileName() {
		return fileName;
	}
	
	public int getFileSize(){
		return fileMessage.length;
	}
	

}
