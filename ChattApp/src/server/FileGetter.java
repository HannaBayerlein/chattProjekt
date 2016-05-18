package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class FileGetter extends Thread {
	private FileBox fileBox;
	private ArrayList<User> users;

	public FileGetter(FileBox fileBox, ArrayList<User> users) {
		this.fileBox = fileBox;
		this.users = users;
	}

	public void run() {
		FileMessage fileMessage;
		byte[] fileData;
		String fileName;
		int fileSize;

		try {
			while (true) {

				fileMessage = fileBox.downloadFile();
				fileName = fileMessage.getFileName();
				fileData = fileMessage.getFile();
				fileSize = fileMessage.getFileSize();
				Socket tempSocket;
				Iterator<User> itr = users.iterator();

				while (itr.hasNext()) {
					tempSocket = itr.next().getFileSocket();
					write(tempSocket, fileName, fileSize, fileData);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(Socket socket, String fileName, int fileSize, byte[] fileData) {

		try {
			DataOutputStream dout = new DataOutputStream(socket.getOutputStream());

			dout.writeUTF(fileName);
			dout.writeLong(fileSize);
			dout.write(fileData); //R�cker det?
			dout.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
