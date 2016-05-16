package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import Client2.Client;
import server.Message;
import server.User;

public class Controller { //Controller ska ha koll på input/output-stream och sen skicka infon till Modeln.
	Model model = null;
	public Controller(){
		
	}
	public void setModel(Model m){
		model=m;
	}
	public void send(String message) { //prata med server och uppdatera modeln.
		
		

	}
	public void receive(String message) { //anropas av read client
		
		Message m = new Message(null, message);
		model.addMessage(m);
	
	}
	public void send(User user, String text){
		
		//pratar med Servern...
		

//		System.out.println(user.getNick() + " Skriver nu");
		Message message = new Message(user,text);
		model.addMessage(message);
	}
	public boolean Login(String name) throws IOException{
		Client client = new Client(this);
		client.start();
		
		//outputStream name. 
	
		client.sendMessageToServer(name);
		//server: skickar User som plockas upp här.
		
	
		//Här kollar man också om det finns någon med samma namn.. 
		
		User user= new User(null,name);
		model.addUser(user);
		return true;
	}
}
