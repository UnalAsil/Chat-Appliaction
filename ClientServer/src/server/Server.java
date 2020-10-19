package server;

import java.net.ServerSocket;
import java.util.logging.Level; 
import java.util.logging.Logger;

import client.Client;

import java.net.Socket;
import java.util.ArrayList;

/**
 * 
 * It meets incoming connection requests. It runs a separate thread for each request.
 * @author unal
 *
 */
public class Server {
	
	private static final Logger LOGGER = Logger.getLogger( Client.class.getName() );

	private ServerWorker worker;
	private static ArrayList<ClientsInfo> clients = new ArrayList<ClientsInfo>();
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("Waiting for clients");
			//TODO Host/port bilgilerini kullanicidan al.
			ServerSocket serverSocket = new ServerSocket(9806);
			
			LOGGER.log(Level.INFO, "Server started.");
			
			while (true) {
				
				Socket socket = serverSocket.accept();
				clients.add(new ClientsInfo(socket));
				LOGGER.log(Level.INFO, "Connection Establishment to Client");

				connectedClientsInfo();
				ServerWorker worker = new ServerWorker(socket);
				worker.start(); // Multi Client support.
			}
			
		}
		catch (Exception e){
			
			LOGGER.log( Level.SEVERE, e.toString(), e );	
			
		}
	}
	
	/**
	 * Print information of clients connected to the server.
	 * 
	 */
	private static void connectedClientsInfo() {
		
		for(ClientsInfo client: clients) {
			
			System.out.println("Connected clients name : " + client.getHostName() + " adress : " + client.getHostAdress());
			
		}
	}
}
