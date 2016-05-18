package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class SendFileButtonListener implements ActionListener, KeyListener {

    Controller controller=null;
    GUI gui;
    final JFileChooser fileDialog= new JFileChooser();

    public SendFileButtonListener(){

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
    	JFrame frame = new JFrame();
    	int returnVal = fileDialog.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
     //      java.io.File file = fileDialog.getSelectedFile();
           System.out.println("SendFileButtonlistner.action: " + fileDialog.getSelectedFile().getAbsolutePath());

           String filePath = fileDialog.getSelectedFile().getAbsolutePath();
           controller.sendFile(filePath);
        }
        else{
           System.out.println("Open command cancelled by user." );           
        } 
        frame.setVisible(true);
        
        
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