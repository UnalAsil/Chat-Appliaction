package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import message.*;

public class Client {
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	 private static ObjectOutputStream outputStream = null;
	Socket socket ;

	public Client() throws IOException {
		connect();
		outputStream = new ObjectOutputStream(socket.getOutputStream());

	}
	public void connect(){
		//TODO Servere baglan.
		try {
			socket = new Socket("localhost", 9806);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client Conncected");
	}
	
	public void queryMessage() {
		//TODO Gelen mesaj var mi diye servere sor.
	}
	
	
	public void closeConnection() {
		//TODO Server baglantisini kapat.
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Conservation ended.");
	}
	 
	public void sendMessage (String to, String cc, String subject, String priorty, String message , String from ) {
		Message mes = new Message(to,cc,subject,priorty, message , from);
		try {

			outputStream.writeObject(mes); //Send to Server message object.
			outputStream.reset();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
