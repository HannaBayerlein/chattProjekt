public class MailBox {

	private String mail;
	private User user;

	// private String userName;

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
		// System.out.println("Mailboxmeddelande addat " + mail);
		this.user = user;
		// userName = userNick;
		// userMessage.set(mail, userName);
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
		// System.out.println("Mailboxmeddelande hämtat " + mail);
		mail = "";
		user = null;
		notifyAll();

		return temp;
	}
}