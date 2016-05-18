package GUI;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ChattroomPane extends JPanel{
	JTextField MessageField;
	JTextArea Chattroom;
	JButton SendButton;
	JButton SendFileButton;
	
	public ChattroomPane(){
		//Chattf�nster
		Chattroom = new JTextArea(18,37); 

		Chattroom.setEditable(false);
		
		JScrollPane scroll = new JScrollPane (Chattroom);
		
		
//		JScrollPane srcoll = new JScrollPane(Chattroom);
//		JScrollPane scrollPane = new JScrollPane(mainConsole);
//		scrollPane.setBounds(10,60,780,500);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		
		//meddelanderutan
		MessageField = new JTextField(30);
		MessageField.setSize(300, 20);
		MessageField.setAlignmentX(LEFT_ALIGNMENT);
		MessageField.setAlignmentY(BOTTOM_ALIGNMENT);
		
		//Send-knappen
		
		SendButton = new JButton("Send");

		SendButton.setToolTipText("Klicka p� knappen f�r att skicka meddelande");
		
		SendFileButton =new JButton("Send File");
		SendFileButton.setToolTipText("Klicka på knappen för att skicka en fil");
		
	//	SendButton.addActionListener(actionlistener);
//		SendButton.addKeyListener(new SendButton(MessageField));
		
	//	SendButtonListener listener = new SendButtonListener();
//		MessageField.addActionListener(actionlistener);
//		SendButton.addKeyListener(keylistener);
		
		//Alla komponenter l�ggs till i Panel:n s� de syns i GUI:t
//		getContentPane().add(scroll);
		//add(Chattroom);
		add(scroll);
		add(MessageField);
		add(SendButton);
		add(SendFileButton);

	}
	public String getMessage(){
		return MessageField.getText();
	}
	public void ClearMessageField(){
		MessageField.setText("");
	}
	public void setChattroomText(String text){
		Chattroom.setText(Chattroom.getText()+"\n"+ text);
	}
	public void setSendButtonListener(SendButtonListener listener){
		SendButton.addActionListener(listener);
		MessageField.addActionListener(listener);
		SendButton.addKeyListener(listener);
	}
	public void setSendFileButtonListener(SendFileButtonListener listener){
		SendFileButton.addActionListener(listener);
		SendFileButton.addKeyListener(listener);
	}
	
}
