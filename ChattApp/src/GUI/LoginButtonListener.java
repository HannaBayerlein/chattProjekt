package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginButtonListener implements ActionListener, KeyListener {

	    Controller controller=null;
	    GUI gui;

	    public LoginButtonListener(){

	    }
	    public void setController(Controller c){
			controller=c;
		}
	    public void setGUI(GUI g){
	    	gui=g;
	    }

	    @Override
	    public void actionPerformed(ActionEvent submitClicked) {
	    	if (!(gui.getUserName().equals("") )) {
	    		gui.ceateUser(gui.getUserName());
	    		if(controller.Login(gui.getUserName())){
	    			gui.ShowChattRoomGUI();
	    		}
	    	}else{
	    		System.out.println("Du måste fylla i ett namn");
	    	}
				
	    }
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

}
