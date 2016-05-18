
package Client2;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ReadFile extends Thread {

	private Socket socket;

	public ReadFile(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		boolean isOnline = true;
		String fileName;
		int fileSize;
		while (isOnline) {
			try {

				DataInputStream dis = new DataInputStream(socket.getInputStream());

				/**
				 * receives name of file and creates file in C:/Users/felicia/
				 */
				fileName = dis.readUTF();
				File newFile = new File("/Users/Elin/Desktop/" + fileName);

				/** receives size of file */
				fileSize = (int) dis.readLong();
				System.out.println("Filename is: " + fileName + " File size is: " + fileSize);

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
				fout.close();

				fout.flush();
//				fout.close();
//				dis.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
