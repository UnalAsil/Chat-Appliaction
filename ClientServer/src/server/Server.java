package server;

import java.net.ServerSocket;
import java.net.Socket;
public class Server {
	

	ServerWorker worker;
	
	
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			while(true) {
				Socket socket = serverSocket.accept();
				System.out.println("Connection Establishment to Client");
				
				ServerWorker worker = new ServerWorker(socket);
				worker.start(); // Multi Client support.
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
