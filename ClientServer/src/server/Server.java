package server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// GG:
// Javadoc
public class Server {
	
	// GG:
	// Encapsulation: bu alanlar dışarıdan erişilmiyor, privae olmalı 
	ServerWorker worker;
	static ArrayList<ClientsInfo> clients = new ArrayList<ClientsInfo>();
	
	public static void main(String[] args) {
		try {
			System.out.println("Waiting for clients");
			// GG:
			// Host/port vb bilgiler ayarlanabilir olmalı, kullanıcıdan alınmalı, kodun içine gömülmemeli
			ServerSocket serverSocket = new ServerSocket(9806);
			// GG:
			// Okunabilirlik açısında try/catch/for/while vb keywordler sonrasında boşluk olmalı
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
			// GG:
			// Loglama
			e.printStackTrace(); 
		}
	}

	// GG:
	// 1- Javadoc
	// 2- Metot ismi yanlış yönlendiriyor, birşey 'get' edilmiyor, metot ve değişken isimleri açıklayıcı ve doğru seçilmeli
	private static void getClientsInfo() {
		// TODO Auto-generated method stub
		// GG:
		// Okunabilirlik açısında try/catch/for/while vb keywordler sonrasında boşluk olmalı
		for(ClientsInfo client: clients) {
			System.out.println("Connected clients name : " + client.getHostName() + " adress : " + client.getHostAdress());
		}
	}
}
