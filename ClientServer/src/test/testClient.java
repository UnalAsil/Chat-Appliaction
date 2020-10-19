package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import client.Client;
import message.Message;
import server.ServerWorker;

/**
 * Its designed for test client side.
 * @author unal
 *
 */
public class testClient {

	private Client testClient;
	private ServerWorker testWorker;
	
	private final int PORT = 9806;
	
	/**
	 * Set up before  execute tests.
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		
		
		ServerSocket serverSocket = new ServerSocket(9806);

		Socket socket = serverSocket.accept();
		
		testClient = new Client();

		testWorker = new ServerWorker(socket);
		
		testWorker.start();
		
	}
	
	/**
	 * Test for message create properly in sendMessage function.
	 */
	@Test
	public void messageCouldntCreateProperly() {
		assertEquals(new Message("aa", "bb", "cc", "dd", "ee", "gg"), testClient.sendMessage("aa", "bb", "cc", "dd", "ee", "gg"));
	}
	
	/**
	 * Test for message receive properly from client.
	 */
	@Test
	public void messageRecieveProperly() {
//		assertEquals()
	}
	
}
