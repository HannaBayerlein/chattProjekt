package GUI;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import server.Message;
import server.User;

public class Model extends Observable{   //Modeln får info från Controller och lägger till info på något ställe och Notify:ar sen GUI:it
	ArrayList<String> users = new ArrayList<String>();
	ArrayList<String> messages= new ArrayList<String>();
	public Model(){
		
	}
	public void updateUsers(ArrayList<String> userList){
		
		setChanged();
		notifyObservers(userList);
	}
	public void addUser(String user){

		users.add(user);
		setChanged();
		notifyObservers(users);
	}
	public void removeUser(User user){
		users.remove(user);
		setChanged();
		notifyObservers(users);
	}
	public void addMessage(String message){
		messages.add(message);
		setChanged();
		notifyObservers(message);
	}
	
	
}
