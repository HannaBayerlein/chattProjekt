package skräpTyp;

import server.Message;
import server.User;

public class FileBox2 {

	private String mail;
	private User user;

		

		public FileBox2() {
			mail = "";
			user = null;
		}

		public synchronized void add(User user, String s)
				throws InterruptedException {
			while (!mail.equals("")) {
				wait();
			}

			mail = s;
			this.user = user;
			notifyAll();

		}

		public synchronized Message getMessage() {

			while (mail.equals("")) {
				try {
					wait();
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			Message temp = new Message(user, mail);
			mail = "";
			user = null;
			notifyAll();
			return temp;
		}
	}
