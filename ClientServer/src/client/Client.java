package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import message.*;

public class Client {
	
	private static final Logger LOGGER = Logger.getLogger( Client.class.getName() );
	
	private static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	private static ObjectOutputStream outputStream = null;
	private Socket socket ;

	public Client() throws IOException {
		
		connect();
		outputStream = new ObjectOutputStream(socket.getOutputStream());

	}
	
	public void connect(){
		//TODO Servere baglan.
		try {
			socket = new Socket("localhost", 9806);
		}
		catch (UnknownHostException e) 
		{
			LOGGER.log( Level.SEVERE, e.toString(), e );	
		}
		catch (IOException e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );	
		}
		System.out.println("Client Conncected");
	}
	
	public void queryMessage() {
		//TODO Gelen mesaj var mi diye servere sor.
	}
	
	public void closeConnection() {
		try {
			
			socket.close();
			
		} catch (IOException e) {
			
			LOGGER.log( Level.SEVERE, e.toString(), e );	
			
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
			LOGGER.log( Level.SEVERE, e.toString(), e );	
		}
		
	}
}
