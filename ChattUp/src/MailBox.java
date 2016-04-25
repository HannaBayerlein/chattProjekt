
public class MailBox {

	private String mail;
	private User user;
//	private String userName;
//	private MessageOfUser userMessage;

	public MailBox() {
		mail = "";
		user = null;
	}

	public synchronized void add(User user, String s) throws InterruptedException {
		while (!mail.equals("")) {
			wait();
		}
	
		mail = s;
		this.user = user;
//		userName = userNick;
//		userMessage.set(mail, userName);
		notifyAll();
		
	}

	public synchronized Message getMessage() throws InterruptedException {

		while (mail.equals("")) {
			wait();
		}
		Message temp = new Message(user, mail);
		mail = "";
		user = null;
		notifyAll();
		return temp;
	}

//
//public class MessageOfUser{
//	private String message;
//	private String userNick;
//	
//	public MessageOfUser(){
//		
//		
//	}
//	
//	public void set(String userNick, String message){
//		this.message = message;
//		this.userNick = userNick;
//	}
	
	
//}
}