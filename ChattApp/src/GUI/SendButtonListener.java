package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SendButtonListener implements ActionListener, KeyListener {

    Controller controller=null;
    GUI gui;

    public SendButtonListener(){

    }
    public void setController(Controller c){
		controller=c;
	}
    public void setGUI(GUI g){
    	gui=g;
    }

    @Override
    public void actionPerformed(ActionEvent submitClicked) {
			action();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	System.out.println("15");

			action();
		
   
    }
    private void action() {
    	String message = gui.getMessage();
    	gui.ClearMessageField();
    	System.out.println(message);
      	
    	controller.send(message);
    }
    
    
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
    	System.out.println("1");
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    	System.out.println("2");
    }
}