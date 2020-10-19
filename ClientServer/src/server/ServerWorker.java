package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import client.Client;
import message.Message;
/**
 * 
 * It is designed to support multiple threads for each connected client.
 * @author unal
 *
 */
public class ServerWorker extends Thread {
	
	private static final Logger LOGGER = Logger.getLogger( ServerWorker.class.getName() );

	private static final String lowPriFile="lowPriorty.txt";
	private static final String midPriFile="midPriorty.txt";
	private static final String highPriFile="highPriorty.txt";
	
	private final Socket socket;
	
	private BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	private ObjectInputStream inStream = null;

	/**
	 * initial socket in thread;
	 * @param serverSocket specified socket
	 */
	
	public ServerWorker (Socket serverSocket) {
		
		this.socket = serverSocket;
	}
	
	/**
	 * Write to files according to priority
	 * @param message Given message object
	 */
	public void ServerFileSys(Message message) {
		
		switch (message.getPriorty()) {
		
			case "Dusuk":  WriteObjectToFile(message, lowPriFile);
						break;
			case "Normal": WriteObjectToFile(message, midPriFile);
						break;
			case "Yuksek": WriteObjectToFile(message, highPriFile);
						break;
			default      : WriteObjectToFile(message, lowPriFile); //In wrong usage.
						break;
		}
	}
	
	private void WriteObjectToFile(Message message, String path) {
		
	    try {
	    	
			FileOutputStream fileOut = new FileOutputStream(path);
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(message);
	        objectOut.close();
	        System.out.println("The Object  was succesfully written to a file");
	        
		} catch (Exception e) {
			LOGGER.log( Level.SEVERE, e.toString(), e );	
		}
	}
	
	/**
	 * Read the objects from given addres
	 * @param path file adress
	 */
	private void ReadObjectFromFile(String path) {
		
		try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Message obj = (Message) objectIn.readObject();
            System.out.println("The Object has been read from the file : " + obj);
		}
		catch(Exception e) {
			LOGGER.log( Level.SEVERE, "There was a problem reading from the file.", e );	
		}
	}
	
	/**
	 * Run thread for individual client
	 */
	@Override
	public void run() {
		
		try {
			
			handleConnection();
			
		} catch (ClassNotFoundException e) {
			
			LOGGER.log( Level.SEVERE, "Class not found", e );	
			
		} catch (SQLException e) {
			
			LOGGER.log( Level.SEVERE, "There is a problem with the database", e );	
			
		} catch (IOException e) {
			
			LOGGER.log( Level.SEVERE, "There is a problem with the file system", e );	
			
		}
	}
	
	/**
	 * Communicate with client and write incoming messages to database according to their priority
	 * 
	 * @throws SQLException when problem in database
	 * @throws IOException when problem in file system.
	 * @throws ClassNotFoundException when problem in read objects.
	 */
	
	public void handleConnection() throws SQLException, IOException, ClassNotFoundException {
		
		ObjectInputStream inStream;
		boolean flag = true;
		
		WriteToDb sqlConnect = new WriteToDb(); //Connection to Database.
//		sqlConnect.connect();
		
		
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
		inStream = new ObjectInputStream(socket.getInputStream());
		
		while(flag)
		{
			Message mes = (Message)inStream.readObject();
			if (mes == null) { //Client disconnect.
				flag = false;
			}
			
			else {
				System.out.println("Client : " + mes ); //Receive from Client;
//				ServerFileSys(mes); // Write message to file 
				
				sqlConnect.insertDBWithThread(mes);
				
//				ReadObjectFromFile("lowPriorty.txt"); 
			}
			
		}
		LOGGER.log( Level.ALL, "Connection closed" );	
		socket.close();
	}
}
