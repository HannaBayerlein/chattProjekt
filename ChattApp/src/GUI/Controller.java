package GUI;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import Client2.Client;
import server.User;

public class Controller { //Controller ska ha koll på input/output-stream och sen skicka infon till Modeln.
	Model model = null;
	Client client =null;
	public Controller(){
		
	}
	public void setModel(Model m){
		model=m;
	}
	public void send(String message) { //prata med server och uppdatera modeln.
		
		

	}
	public void receive(String message) { //anropas av read client
		System.out.println(message);
		
		if(message.contains("&&&")){
			String[] users = message.split("&&&");
			ArrayList<String> userList = new ArrayList<String>(Arrays.asList(users));
			String joindmsg=userList.remove(0);
			model.updateUsers(userList);
			model.addMessage(joindmsg); 
			
		}else{
			model.addMessage(message);
		}
	}
	public void send(User user, String text){
		
		//pratar med Servern...
		
		client.sendMessageToServer(text);

		//model.addMessage(text);
	}
	public void addClient(Client c){
		client = c;
	}
	
	public boolean Login(String name) throws IOException{
		
			if(!(client.getSocket() == null)){
			client.sendMessageToServer(name); //outputStream name. 
		
		//server: skickar User som plockas upp här.
		//String answer = client.receiveLoginUser();
		//Här kollar man också om det finns någon med samma namn.. 
		
		//User user= new User(null,name);
		//model.addUser(name);
		
			}
		
		return true;
	}
}
