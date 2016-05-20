
package Client2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import GUI.Controller;

public class ReadFile extends Thread {

	private Socket fileSocket;
	private Controller controller;

	public ReadFile(Socket fileSocket, Controller controller) {
		this.fileSocket = fileSocket;
		this.controller =controller;
	}

	public void run() {
		boolean isOnline = true;
		String fileName;
		int fileSize;
		int index = 1;
		
		System.out.println("i ReadFile nu, utanför try..");
			try {
				DataInputStream dis = new DataInputStream(fileSocket.getInputStream());
				while (isOnline) {
				System.out.println("i ReadFile nu..G�ng: "+index);
				index++;
				
				System.out.println(fileSocket.getInputStream());
				/**
				 * receives name of file and creates file in C:/Users/felicia/
				 */
				fileName = dis.readUTF();
				System.out.println("filname fr�n dis: "+fileName);
				File newFile = new File("/Users/Elin/Desktop/" + fileName);

				/** receives size of file */
				fileSize = (int) dis.readLong();
				System.out.println("Filename is: " + fileName + " File size is: " + fileSize);
				controller.receive("You just recived a file: " + fileName);
				
				/** save on byte array byteFile by using fileoutputstream */
				byte[] byteFile = new byte[fileSize];
				int read = 0;
				int result = 0;

				while (read < fileSize && result != -1) {
					result = dis.read(byteFile, read, fileSize - read);
					if (result != -1)
						read = read + result;
				}

				/** read bytes from byte array byteFile to actual file */

				FileOutputStream fout = new FileOutputStream(newFile);
				fout.write(byteFile);
				//fout.close();

				fout.flush();
				
				fout.close();
//				dis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		
	}
}
