package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;



import message.*;


/**
 * 
 * It describes the client side. It can connect to a specific address and communicate with the address over the socket.
 * @author unal
 */

public class Client {
	
	private static final Logger LOGGER = Logger.getLogger( Client.class.getName() );
	private Socket socket ;

	
	public Client() throws IOException {
		
		connect();
	}
	/**
	 * Connect specified address and port.
	 */
	private void connect(){
		
		Scanner myScan = new Scanner(System.in);

		try {
			
			//TODO Host/port bilgilerini kullanicidan al.
			System.out.println("Enter a host address");
			final String HOST = myScan.nextLine();
			System.out.println("Enter a port Number");
			final int PORT = myScan.nextInt();

			socket = new Socket(HOST, PORT);
		}
		
		catch (UnknownHostException e) 
		{
			
			LOGGER.log( Level.SEVERE, "Could not connect to the server. unknown host", e);	
		}
		
		catch (IOException e) {
			
			LOGGER.log( Level.SEVERE, e.toString(), e );	
		}
		
		LOGGER.log( Level.INFO, "Client connect the server" );	
		
	}
	
	private void queryMessage() {
		//TODO Gelen mesaj var mi diye servere sor.
	}

	public void closeConnection() {
		try {
			
			socket.close();
			
		} catch (IOException e) {
			
			LOGGER.log( Level.SEVERE, "The socket could not be turned off. IO error", e );	
			
		}
		System.out.println("Conservation ended.");
	}
	
	/**
	 * Send message to server
	 * @param to Address to which the message will be sent
	 * @param cc Addresses to send a copy of the message
	 * @param subject Title of the message
	 * @param priorty Priority of message
	 * @param message Content of the message
	 * @param from  Address to which the message was sent.
	 */
	public Message sendMessage (String to, String cc, String subject, String priorty, String message , String from ) {
		
		Message mes = new Message(to,cc,subject,priorty, message , from);
		
		ObjectOutputStream outputStream = null;
		
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e1) {
			LOGGER.log( Level.SEVERE, "Could not create socket", e1 );	
		}
		
		try {
			
			outputStream.writeObject(mes); //Send to Server message object.
			outputStream.reset();
			
		}
		catch(Exception e){
			LOGGER.log( Level.SEVERE, "Could not send message from client to server", e );	
		}
		
		return mes;
	}
	
	public Message sendMessage (String to, String cc, String subject, String priorty, String message , String from, int id ) {
		
		Message mes = new Message(to,cc,subject,priorty, message , from, id);
		
		ObjectOutputStream outputStream = null;
		
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
		}
		catch (IOException e1) {
			LOGGER.log( Level.SEVERE, "Could not create socket", e1 );	
		}
		
		try {
			
			outputStream.writeObject(mes); //Send to Server message object.
			outputStream.reset();
			
		}
		catch(Exception e){
			LOGGER.log( Level.SEVERE, "Could not send message from client to server", e );	
		}
		
		return mes;
	}
	
}
