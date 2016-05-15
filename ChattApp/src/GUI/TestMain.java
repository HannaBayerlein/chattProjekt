package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Client.Client;
import server.*;

public class TestMain {
	public static void main(String[] args) {

		GUI gui = new GUI();
		Model model = new Model();
		
		model.addObserver(gui);  //gör att gui:t kollar på modeln och gör stuff när den blir notified
		
	
		Controller con = new Controller();
		con.setModel(model);
		
		SendButtonListener sbl = new SendButtonListener();
		sbl.setController(con);
		sbl.setGUI(gui);
		
		gui.SetListeners(sbl);
		
		LoginButtonListener lbl = new LoginButtonListener();
		lbl.setController(con);
		lbl.setGUI(gui);
		
		gui.SetListenerLogin(lbl);
		
	}	
}
