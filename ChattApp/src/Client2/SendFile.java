package Client2;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class SendFile extends Thread {
	private Socket socket;

	public SendFile(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		boolean isOnline = true;

		int i;
		while (isOnline) {
			try {
				DataOutputStream os = new DataOutputStream(socket.getOutputStream());
				File file = new File("/Users/Elin/Downloads/hund.jpg");
		//		File file = new File("C:/Users/felicia/image.JPG");
	//			File file = new File("C:/Users/felicia/Test1.txt");
				
				/** skickar filnamn */
				os.writeUTF("SendFile.jpg");
				os.flush();
				
				/** sending file lenght*/
				os.writeLong(file.length());
				os.flush();

				/** get file and send it on outputstream */
				FileInputStream fis = new FileInputStream(file);
				
					while ((i = fis.read()) > -1) {
						os.write(i);
					}
					
				os.flush();
//				fis.close();
			

			} catch (Exception e) {
				System.out.println("Could not receive picture");

				e.printStackTrace();
			}
		}

	}
}
