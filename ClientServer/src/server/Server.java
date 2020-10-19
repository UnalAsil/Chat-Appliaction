package server;

import java.net.ServerSocket;
import java.util.logging.Level; 
import java.util.logging.Logger; 

import java.net.Socket;
import java.util.ArrayList;
public class Server {
	

	private ServerWorker worker;
	private static ArrayList<ClientsInfo> clients = new ArrayList<ClientsInfo>();
	
	public static void main(String[] args) {
		
		try {
			
		       Logger logger 
	            = Logger.getLogger( 
	            		Server.class.getName()); 
	  
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			
			logger.log(Level.INFO, "Server started.");
			
			while (true) {
				
				Socket socket = serverSocket.accept();
				clients.add(new ClientsInfo(socket));
				logger.log(Level.INFO, "Connection Establishment to Client");

				connectedClientsInfo();
				ServerWorker worker = new ServerWorker(socket);
				worker.start(); // Multi Client support.
			}
			
		}
		catch (Exception e){
			
			e.printStackTrace(); 
			
		}
	}

	private static void connectedClientsInfo() {
		
		for(ClientsInfo client: clients) {
			
			System.out.println("Connected clients name : " + client.getHostName() + " adress : " + client.getHostAdress());
			
		}
	}
}
