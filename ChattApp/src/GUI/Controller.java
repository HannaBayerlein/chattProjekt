package GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

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
		
		
		//PrintStream print = new PrintStream(socket.getOutputStream());
//		print.println("Välj användarnamn:");
	}
	public void receive() throws IOException{
//		InputStreamReader in = new InputStreamReader(socket.getInputStream());
//		BufferedReader buffer = new BufferedReader(in);
		
	}
	public void send(User user, String text){
		
		//pratar med Servern...
		

		System.out.println(user.getNick() + " Skriver nu");
		Message message = new Message(user,text);
		model.addMessage(message);
	}
	public boolean Login(String name){
		//här conectar man med 30000 osv ?.. och ger då en socket till användaren
		//Här kollar man också om det finns någon med samma namn.. 
		
		User user= new User(null,name);
		model.addUser(user);
		return true;
	}
}
