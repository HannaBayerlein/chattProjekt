package server;

public class FileBox {
	private FileMessage file;

	public FileBox() {
		file = null;
	}

	public synchronized void uploadFile(User user, String fileName, byte[] fileData) throws InterruptedException {

		file = new FileMessage(user, fileName, fileData);
		notifyAll(); // vad h�nder h�r?

	}

	public synchronized FileMessage downloadFile() {
		while (file == null) {
			try {
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		FileMessage tempFile = file;
		file = null;
		notifyAll(); 
		return tempFile;

	}
}
