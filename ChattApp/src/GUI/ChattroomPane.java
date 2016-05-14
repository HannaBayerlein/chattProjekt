package GUI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ChattroomPane extends JPanel{
	public ChattroomPane(){
		//Chattf�nster
		JTextArea Chattroom = new JTextArea(18,37); 
		Chattroom.setEditable(false);
		
		JScrollPane scroll = new JScrollPane (Chattroom);
		
		
//		JScrollPane srcoll = new JScrollPane(Chattroom);
//		JScrollPane scrollPane = new JScrollPane(mainConsole);
//		scrollPane.setBounds(10,60,780,500);
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		
		//meddelanderutan
		JTextField MessageField = new JTextField(30);
		MessageField.setSize(300, 20);
		MessageField.setAlignmentX(LEFT_ALIGNMENT);
		MessageField.setAlignmentY(BOTTOM_ALIGNMENT);
		
		//Send-knappen
		JButton SendButton = new JButton("Send");
		SendButton.setToolTipText("Klicka p� knappen f�r att skicka meddelande");
		
		SendButton.addActionListener(new SendButton(MessageField, Chattroom));
//		SendButton.addKeyListener(new SendButton(MessageField));
		
		SendButton listener = new SendButton(MessageField, Chattroom);
		MessageField.addActionListener(listener);
		SendButton.addKeyListener(listener);
		
		//Alla komponenter l�ggs till i Panel:n s� de syns i GUI:t
//		getContentPane().add(scroll);
		//add(Chattroom);
		add(scroll);
		add(MessageField);
		add(SendButton);

	}

}
