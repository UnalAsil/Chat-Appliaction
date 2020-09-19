package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		try {
			boolean flag = true;
			System.out.println("Waiting for clients");
			ServerSocket serverSocket = new ServerSocket(9806);
			Socket socket = serverSocket.accept();
			System.out.println("Connection Establishment");
			
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			
			System.out.println("Send message in this format : To - Cc - Subject - Priorty - Message");
			System.out.println("Priority must be one in these: Düşük - Normal - Yuksek ");
			while(flag)
			{
				String comingMsg = in.readLine();
				if (comingMsg == null) { //Client disconnect.
					flag = false;
				}
				
				else {
					System.out.println("Client : "+comingMsg ); //Recieve from Client;
					
					String userMsg = userInput.readLine(); // Read from Console;
					out.println(userMsg); //Send to Client;
				}
				
			}
			System.out.println("Conservation ended.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
