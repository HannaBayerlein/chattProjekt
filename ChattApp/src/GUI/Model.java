package GUI;

import java.awt.List;
import java.util.ArrayList;
import java.util.Observable;

import server.Message;
import server.User;

public class Model extends Observable{   //Modeln får info från Controller och lägger till info på något ställe och Notify:ar sen GUI:it
	ArrayList<User> users = new ArrayList<User>();
	ArrayList<Message> messages= new ArrayList<Message>();
	public Model(){
		
	}
	public void addUser(User user){
		users.add(new User(null,"Hanna"));
		users.add(new User(null,"Felicia"));
		users.add(new User(null,"Elsa"));
		users.add(new User(null,"Firefox"));
		users.add(new User(null,"Tanten"));
		users.add(user);
		setChanged();
		notifyObservers(users);
	}
	public void removeUser(User user){
		users.remove(user);
		setChanged();
		notifyObservers(users);
	}
	public void addMessage(Message message){
		messages.add(message);
		setChanged();
		notifyObservers(message);
	}
	
	
}
