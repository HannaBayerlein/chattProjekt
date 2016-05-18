package Client2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import GUI.Controller;
import Client2.ReadFile;
import Client2.SendFile;


public class Client extends Thread {
	private ReadClient rClient;
	private WriteClient wClient;
	private ReadFile rFile;
	private SendFile sFile;
	Socket socket;
	Socket fileSocket;
	private Controller controller;

	public Client(Controller controller) {
		this.controller =controller;
	}

	public void run() {
		System.out.println("Connecting to server on port 30000");
		
		try {
			socket = new Socket("localhost", 30000);
			fileSocket = new Socket("localhost", 30000);
			System.out.println("Just connected to " + socket.getInetAddress() + " and " + fileSocket.getInetAddress());
	
			rClient = new ReadClient(socket,controller);
			rFile = new ReadFile(fileSocket);
			sFile = new SendFile(fileSocket);
			rClient.start();
			rFile.start();
			sFile.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public Socket getSocket(){
		return socket;
	}
	public void sendMessageToServer(String message) {
		PrintStream print;
		try {
			socket = getSocket();
			if(socket==null){
				System.out.println("socket �r null.");
			}
			print = new PrintStream(socket.getOutputStream());
			print.println(message);
			//ssocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	public String receiveLoginUser(){
		String answer=null;
		try {
			socket = new Socket("localhost", 30000);
		InputStreamReader in = new InputStreamReader(
				socket.getInputStream());
		BufferedReader buffer = new BufferedReader(in);
		answer = buffer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		try {
//			socket.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return answer;
	}
	
}
