package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import message.*;

public class Server {
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	static ObjectInputStream inStream = null;
	public static void main(String[] args) {
		try {
			boolean flag = true;
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			Socket socket = serverSocket.accept();
			System.out.println("Connection Establishment");
			
			
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
					
				}
				
			}
			System.out.println("Conservation ended.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
