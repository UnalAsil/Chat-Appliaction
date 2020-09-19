package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		
		try {
			boolean flag = true;
			System.out.println("Client starded");
			Socket socket = new Socket("localhost", 9806);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println("Send message in this format : To - Cc - Subject - Priorty - Message");
			System.out.println("Priority must be one in these: Düşük - Normal - Yuksek ");

			while(flag) {
				String userMsg = userInput.readLine(); //Read from console
				if(userMsg.equals("Exit")){
					flag = false;
				}
				else 
				{
					out.println(userMsg); //Send to Server
					System.out.println("Server :" + in.readLine()); //Recieve from Server
				}
			}
			socket.close();
			System.out.println("Conservation ended.");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
