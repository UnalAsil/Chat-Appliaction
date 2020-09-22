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

import message.Message;

public class ServerWorker extends Thread {
	
	private static final String lowPriFile="lowPriorty.txt";
	private static final String midPriFile="midPriorty.txt";
	private static final String highPriFile="highPriorty.txt";
	
	private Socket socket;

	public ServerWorker (Socket serverSocket) {
		this.socket = serverSocket;
	}
	
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	static ObjectInputStream inStream = null;
	
	
	public static void ServerFileSys(Message message) {
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
	
	private static void WriteObjectToFile(Message message, String path) {
	    try {
			FileOutputStream fileOut = new FileOutputStream(path);
	        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
	        objectOut.writeObject(message);
	        objectOut.close();
	        System.out.println("The Object  was succesfully written to a file");
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void ReadObjectFromFile(String path) {
		try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Message obj = (Message) objectIn.readObject();
            System.out.println("The Object has been read from the file : " + obj);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			handleConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
		System.out.println("Conservation ended from client.");
		socket.close();
	}
}
