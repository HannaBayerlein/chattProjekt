package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SendButton implements ActionListener, KeyListener {

    JTextField message;
    JTextArea chattroom;

    public SendButton(JTextField textfield, JTextArea textarea){
        message = textfield;
        chattroom = textarea;
    }

    @Override
    public void actionPerformed(ActionEvent submitClicked) {
    	action();
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if (e.getKeyCode()==KeyEvent.VK_ENTER){
//            System.out.println("Hello");
//        }
       	action();
   
    }
    private void action(){
    	chattroom.setText(chattroom.getText()+message.getText()+"\n");
    	message.setText("");
    }
    
    
    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyTyped(KeyEvent arg0) {

    }
}