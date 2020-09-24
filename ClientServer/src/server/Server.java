package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server {
	

	ServerWorker worker;
	static ArrayList<ClientsInfo> clients = new ArrayList<ClientsInfo>();
	
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection Establishment to Client");
				clients.add(new ClientsInfo(socket));
				getClientsInfo();
				ServerWorker worker = new ServerWorker(socket);
				worker.start(); // Multi Client support.
			}
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}

	private static void getClientsInfo() {
		// TODO Auto-generated method stub
		for(ClientsInfo client: clients) {
			System.out.println("Connected clients name : " + client.getHostName() + " adress : " + client.getHostAdress());
		}
	}
}
