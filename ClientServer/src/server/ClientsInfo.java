package server;

import java.net.Socket;

/**
 * //This class holds the connected clients information.
 * @author unal
 *
 */

public class ClientsInfo { 
//TODO Hold connected clients informations.
	private Socket socket;
	
	private String hostName;

	private String hostAdress;
	
	/**
	 * Hold socket, hostname and hostAdress
	 * @param socket
	 */
	public ClientsInfo(Socket socket) {
		this.socket = socket;
		this.hostName  = socket.getInetAddress().getHostName();
		this.hostAdress  = socket.getInetAddress().getHostAddress();
	}

	public String getHostName() {
		return hostName; 
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getHostAdress() {
		return hostAdress;
	}

	public void setHostAdress(String hostAdress) {
		this.hostAdress = hostAdress;
	}
}
