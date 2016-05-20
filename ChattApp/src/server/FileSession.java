package server;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSession extends Thread {

	private String fileName = "";
	private FileBox fileBox;
	private User user;
	private ArrayList<User> activeUsers;

	public FileSession(FileBox fileBox, User user, ArrayList<User> activeUsers) {
		this.fileBox = fileBox;
		this.user = user;
		this.activeUsers = activeUsers;
	}

	public void run() {

		try {
			while (true) {
				Socket socket = user.getFileSocket();
				DataInputStream dis = new DataInputStream(socket.getInputStream());
				String fileName = dis.readUTF();
				int fileSize = (int) dis.readLong();
				System.out.println(fileName);
				System.out.println(fileSize);

				byte[] byteFile = new byte[fileSize];
				int read = 0;
				int result = 0;

				while (read < fileSize && result != -1) {
					result = dis.read(byteFile, read, fileSize - read);
					if (result != -1)
						read = read + result;
				}

				if (activeUsers.contains(user)) {

					fileBox.uploadFile(user, fileName, byteFile);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}
