package GUI;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUI {
	
	
	public GUI(ArrayList<String> users){

	
	JFrame frame = new JFrame("ChattUp");
	JTabbedPane tabbedPane = new JTabbedPane();			//Delen av framen som är tabbaer
	JPanel userPanel = new JPanel();					//Delen av framen där användarna ska listas
	
	UserPane userpane=new UserPane(users);
//	userpane.setBackground(Color.blue);
	
	userPanel.add(userpane);
	
	chattroomPane CRpane=new chattroomPane();
	
	tabbedPane.addTab("ChattRoom",null, CRpane, "chattroom");
	
	tabbedPane.setSelectedIndex(0);
	
	frame.add(userPanel, BorderLayout.WEST);
	frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	
	frame.setSize(600,400);
	frame.setVisible(true);
	
	
	}
}
