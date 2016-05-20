package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import server.Message;
import server.TCPServer2;
import server.User;

public class GUI implements Observer {
	ChattroomPane CRpane;
	JFrame Chattframe;
	JFrame Loginframe;
	JButton okButton;
	JTextField Userinput;
	UserPane userpane;
	String user;
	
	
	public GUI(){
		showLoginGUI();
		showChattGUI();
	}
	public void setSendButtonListener(ActionListener a){
		
	}
	
	public void showChattGUI(){
	
	Chattframe = new JFrame("ChattUp");

	JTabbedPane tabbedPane = new JTabbedPane();			//Delen av framen som är tabbaer
	JPanel userPanel = new JPanel();					//Delen av framen där användarna ska listas
	
	userpane=new UserPane();
	
	userPanel.add(userpane);
	

	CRpane = new ChattroomPane();
	
	tabbedPane.addTab("ChattRoom",null, CRpane, "chattroom");
	
	tabbedPane.setSelectedIndex(0);
	
	Chattframe.add(userPanel, BorderLayout.WEST);
	Chattframe.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	
	Chattframe.setSize(700,400);
//	Chattframe.setVisible(true);
	
	
	}
	public void showLoginGUI(){
		Loginframe = new JFrame("Login");
		JPanel panel = new JPanel();
		JTextField text = new JTextField();
		text.setText("Välj användarnamn: ");
		text.setEditable(false);
		Userinput = new JTextField(15);

		okButton = new JButton("OK");

//		okButton.addActionListener(new ActionListener() {
//			ArrayList<User> users = TCPServer2.activeUsers;  //måste deklareras här för att de inre metoderna ska nå den.
//			public void actionPerformed(ActionEvent e) {
//				// ArrayList<User> users = null;
//				User user = new User(null, input.getText());
//
//				for (int i = 0; i < users.size(); i++) {
//					User tempUser = users.get(i);
//					if (tempUser.getNick().equals(user.getNick())) {
//						System.out.println("Användarnamnet används redan, välj ett nytt:");
//						// userName = buffer.readLine();
//						// user.setNick(userName);
//						return;
//					}
//				}
//				if (!(input.getText().equals("") )) {
//					users.add(user);
//					//showChattGUI();
//					Chattframe.setVisible(true);
//					frame.hide();
//				} else {
//					System.out.println("Du måste fylla i ett Namn");
//				}
//			}
//		});
		panel.add(text);
		panel.add(Userinput);
		panel.add(okButton);
		Loginframe.add(panel);

		Loginframe.setSize(250, 150);
		Loginframe.setVisible(true);
		
		}
	public void ShowChattRoomGUI(){ //och göm login-fönstet
		Chattframe.setVisible(true);
		Loginframe.hide();
	}
	public void SetListenerLogin(LoginButtonListener listener){
		okButton.addActionListener(listener);
		Userinput.addActionListener(listener);
		okButton.addKeyListener(listener);
	}
	public String getUserName(){
		return Userinput.getText();
	}
	
	@Override
	public void update(Observable o, Object arg) { 	//när man skriver notifyAll gör detta:
		if(arg instanceof String){
			String messagetext= (String) arg;
			setChattroomText(messagetext);
		}
		
		else if (arg instanceof ArrayList<?>){
			setUserList((ArrayList<String>) arg);
			
		}else{
			System.out.println("arg kunde inte tolkas av instance of Message eller ArrayList<?>");
		}
//		System.out.println(user+": "+ messagetext);
		
	}
	public String getMessage(){
		return CRpane.getMessage();
	}
	public void ClearMessageField(){
		CRpane.ClearMessageField();
	}
	public void setChattroomText(String text){
		CRpane.setChattroomText(text);
	}
	public void SetListeners(SendButtonListener listener, SendFileButtonListener filelistener){
		CRpane.setSendButtonListener(listener);
		CRpane.setSendFileButtonListener(filelistener);

	}
	public void setUserList(ArrayList<String> users){
		userpane.setUserList(users);	//user.getNick()
	}
	public void setUserNick(String name){
		user=name;
	}
	public void ceateUser(String name){
		user = name;
	}
	public String getUser(){
		return user;
	}
}

