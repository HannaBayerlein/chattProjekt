import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class User {
	private String nickName;
	private boolean active = false;
	private Socket socket;
	
	public User(Socket socket, String userName){
		this.socket = socket;
		nickName = userName;
		active = true;
	}
	
	public void setNick(String nick){
		nickName = nick;
	}
	
	public String getNick(){
		return nickName;
	}
	
	public boolean isActive(){
		return active;
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	public void write(String message){
		try{
			PrintStream print = new PrintStream(socket.getOutputStream());
			print.println(message);
			System.out.println(nickName + ": " + message);
			read();
			
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void read(){
		
		InputStreamReader in;
		try {
			in = new InputStreamReader(socket.getInputStream());
			BufferedReader buffer = new BufferedReader(in);
			String serverHello = buffer.readLine();
			System.out.println("Userread" + serverHello);
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
}
