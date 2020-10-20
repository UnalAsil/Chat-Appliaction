package test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import client.Client;
import message.Message;
import server.ServerWorker;

/**
 * Its designed for unit test.
 * @author unal
 *
 */
public class testClient {

	private Client testClient;
	private ServerWorker testWorker; 
	
	private final int PORT = 9806;
	
	private ServerSocket serverSocket;
	
	private Socket socket ;
	
	/**
	 * Set up before  execute tests.
	 * @throws IOException
	 */
	@Before
	public void setUp() throws IOException {
		
		System.out.println("Test deneme");
		
		serverSocket = new ServerSocket(9806);
		
		testClient = new Client();

		socket = serverSocket.accept();
		
		System.out.println("Test deneme 2");


		testWorker = new ServerWorker(socket);
		
		testWorker.start();
		
//		socket.close();
//		serverSocket.close();
		
	}
	
	/**
	 * Test for message create properly in sendMessage function.
	 * @throws IOException 
	 */
//	@Test
//	public void messageCouldntCreateProperly() throws IOException {
//		Message mes = new Message("aa", "bb", "cc", "dd", "ee", "gg",2);
//		Message mes2 = testClient.sendMessage("aa", "bb", "cc", "dd", "ee", "gg",2);
//		
//		// may override equal ?
//		
//		assertEquals(mes.getCc(), mes2.getCc());
//		assertEquals(mes.getTo(), mes2.getTo());
//		assertEquals(mes.getFrom(), mes2.getFrom());
//		assertEquals(mes.getMessage(), mes2.getMessage());
//		assertEquals(mes.getSubject(), mes2.getSubject());
//		assertEquals(mes.getPriorty(), mes2.getPriorty());
//		assertEquals(mes.getId(), mes2.getId());
//		
//		
//		}
//	
	
	
	
	
	/**
	 * Test for message receive properly from client.
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	@Test
	public void messageRecieveProperly() throws ClassNotFoundException, IOException {
		Message mes1 = testClient.sendMessage("aa", "bb", "cc", "dd", "ee", "gg");
		Message mes2 = testWorker.getMessageFromSocket();
		Message mes3 = null;
//		assertEquals("Sending and recevibing messages should be same", mes1 , mes3);
		
		assertEquals(mes1.getCc(), mes2.getCc());
		assertEquals(mes1.getTo(), mes2.getTo());
		assertEquals(mes1.getFrom(), mes2.getFrom());
		assertEquals(mes1.getMessage(), mes2.getMessage());
		assertEquals(mes1.getSubject(), mes2.getSubject());
		assertEquals(mes1.getPriorty(), mes2.getPriorty());
		assertEquals(mes1.getId(), mes2.getId());
		
		socket.close();
		serverSocket.close();
	}
}
