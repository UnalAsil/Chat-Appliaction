package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import message.*;

public class Client {
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	 private static ObjectOutputStream outputStream = null;
	public static void main(String[] args) {
		
		try {
			boolean flag = true;
			System.out.println("Client starded");
			Socket socket = new Socket("localhost", 9806);
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			outputStream = new ObjectOutputStream(socket.getOutputStream());

			System.out.println("Enter the TO, Exit for quit");
			System.out.println("Priority must be one in these: Düşük - Normal - Yuksek ");

			while(flag) {
				String userMsg = userInput.readLine(); //Read from console
				
				if(userMsg.equals("Exit")){
					flag = false;
				}
				else 
				{
					String to = userMsg;
					System.out.println("Enter the CC");
					String cc = userInput.readLine();
					System.out.println("Enter the Subject");
					String subject = userInput.readLine();
					System.out.println("Enter the Priorty");
					String priorty = userInput.readLine();
					System.out.println("Enter the Message");
					String message = userInput.readLine();
					Message mes = new Message(to,cc,subject,priorty, message);
					outputStream.writeObject(mes); //Send to Server message object.
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
