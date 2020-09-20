package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import message.*;
import server.WriteToDb;;
public class Server {
	
	private static final String lowPriFile="lowPriorty.txt";
	private static final String midPriFile="midPriorty.txt";
	private static final String highPriFile="highPriorty.txt";
	
	
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
		
	public static void main(String[] args) {
		try {
			boolean flag = true;
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			Socket socket = serverSocket.accept();
			System.out.println("Connection Establishment to Client");
			
			WriteToDb sqlConnect = new WriteToDb(); //Connection to Database.
			sqlConnect.connect();
			
			
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
					System.out.println("Client : "+mes ); //Receive from Client;
					ServerFileSys(mes); // Write message to file 
//					ReadObjectFromFile("lowPriorty.txt"); 
				}
				
			}
			System.out.println("Conservation ended from client.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
