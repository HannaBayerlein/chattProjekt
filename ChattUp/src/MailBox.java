public class MailBox {

	private String mail;
	private User user;

	public MailBox() {
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