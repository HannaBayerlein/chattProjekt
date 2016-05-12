package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.User;

public class TestMain {
	public static void main(String[] args){
		ArrayList<String> users = new ArrayList<String>();
		users.add("Elin");
		users.add("Joel");
		users.add("Hanna");
		users.add("Myntis");
		users.add("Felicia");
		users.add("Elsa");
		
		JFrame frame = new JFrame("Login");
		JPanel panel = new JPanel();
		JTextField text = new JTextField();
		text.setText("Välj Användarnamn: ");
		text.setEditable(false);
		JTextField input = new JTextField(15);
		
//		JButton button = new JButton("OK");
//		button.addActionListener(new ButtonListener());
//		
		JButton okButton = new JButton("OK"); 

		okButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	if(!(input.getText().equals("") && users.contains(input.getText()))){
	//        	User user = new User(null, input.getText() );
	        	users.add(input.getText());		
	            new GUI(users);
	            frame.hide();
	        	}else{
	        		System.out.println("Ogiltligt användarnamn");
	        	}
	         }
		});
		panel.add(text);
		panel.add(input);
		panel.add(okButton);
		frame.add(panel);
		
		frame.setSize(250,150);
		frame.setVisible(true);
				
	}
	public class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
