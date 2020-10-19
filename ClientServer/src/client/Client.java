package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import message.*;

// GG:
// Javadoc olmalı,  sınıfın amacı çok uzatmadan ama anlaşılır seviyede yazılmalı.
public class Client {
	// GG:
	// 1- Field ve metotlar dışarıdan erişilmediği sürece encapsulation açısından private olmalı.
	// 2- Bu tür stream oluşturma gibi işlemler field tanımında yapılmamalı
	static BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
	
	// GG:
	// Kod blokları hizalı olmalı
	 private static ObjectOutputStream outputStream = null;
	Socket socket ;

	public Client() throws IOException {
		connect();
		outputStream = new ObjectOutputStream(socket.getOutputStream());

	}
	
	// GG:
	// 1- Metot neden public ? Dışarıdan erişilmesi gerekmeyen hiçbir alan/metot private olmamalı
	// 2- Metotlardan önce ve sonra okunabilirliği arttırmak için boş satır bulunmalı
	public void connect(){
		//TODO Servere baglan.
		try {
			// GG:
			// Bu tür host, port vb bilgiler ayarlanabilir olmalı, kodun içine gömülmemeli
			socket = new Socket("localhost", 9806);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			// GG:
			// Hataların yazdırılması için loglama kullanılmalı, loglama sırasında yazdırılan mesaj
			// kullanıcı için manalı olmalı
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// GG:
			// Hataların yazdırılması için loglama kullanılmalı, loglama sırasında yazdırılan mesaj
			// kullanıcı için manalı olmalı
			e.printStackTrace();
		}
		System.out.println("Client Conncected");
	}
	
	// GG:
	// Public
	public void queryMessage() {
		//TODO Gelen mesaj var mi diye servere sor.
	}
	
	
	public void closeConnection() {
		//TODO Server baglantisini kapat.
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Conservation ended.");
	}
	 
	public void sendMessage (String to, String cc, String subject, String priorty, String message , String from ) {
		Message mes = new Message(to,cc,subject,priorty, message , from);
		try {

			outputStream.writeObject(mes); //Send to Server message object.
			outputStream.reset();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
