package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

import message.Message;

public class WriteToDb {
private final String url = "jdbc:postgresql://localhost/mesdb";
private final String user = "postgres";
private final String password = "test123";
private Connection connection;

private static Queue<Message> lowPriQueue;
private static Queue<Message> midPriQueue;
private static Queue<Message> highPriQueue;


public WriteToDb() throws SQLException {
	this.connection = DriverManager.getConnection(url, user, password);
	lowPriQueue = new LinkedList<Message>();
	midPriQueue = new LinkedList<Message>();
	highPriQueue = new LinkedList<Message>();
}

public void connect () {
	try{
		if(connection != null) {
			System.out.println("Connected to PostgreSql server successfully");
		}
		else {
			System.out.println("Failed to connect PostgreSql server");
		}
		String query = "Select * from \"midPriorty\";";
		queryDb(query);
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public void queryDb(String query) {
	System.out.println("Here is the query result: " );
	try {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			 for (int i = 1; i < resultSet.getMetaData().getColumnCount() + 1; i++) {
	              System.out.print(" " + resultSet.getMetaData().getColumnName(i) + "=" + resultSet.getObject(i));
	            }
	            System.out.println("");
		}
	}
	catch(Exception e) {
		e.printStackTrace();
	}
}

public void insertDB(Message msg) throws SQLException {
	//TODO Insert message to database table according to priority;
	PreparedStatement st;
	switch (msg.getPriorty()) {
	case "Dusuk":  	st = this.connection.prepareStatement("INSERT INTO \"lowPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					break;
				
	case "Normal": 	st = this.connection.prepareStatement("INSERT INTO \"midPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());			
					break;
	case "Yuksek": 	st = this.connection.prepareStatement("INSERT INTO \"highPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());			
					break;
	default      :	st = this.connection.prepareStatement("INSERT INTO \"lowPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					break;		//In wrong usage.
	}
	st.executeUpdate();
	st.close();
}

public void runInLowQue() throws SQLException {
	Thread lowPriortyDbThread = new Thread("Writing low priorty message to database") {
		Message msg;
		PreparedStatement st;
		Connection connection = DriverManager.getConnection(url, user, password);
		public void run() {
			try {
				while (lowPriQueue.size()>0)
				{
					msg = lowPriQueue.poll();
					System.out.println("Insert low priorty thread running.");
					st = connection.prepareStatement("INSERT INTO \"lowPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					st.executeUpdate();
					st.close();
					System.out.println("Insert low priorty thread runned.");
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	};lowPriortyDbThread.start();
	
}

public void runInMidQueue() throws SQLException{
	Thread midPriortyDbThread = new Thread("Writing mid priorty message to database") {
		Message msg ;
		PreparedStatement st;
		Connection connection = DriverManager.getConnection(url, user, password);
		public void run() {
			try {
				while(midPriQueue.size()>0){
					msg = midPriQueue.poll();
					st = connection.prepareStatement("INSERT INTO \"midPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					st.executeUpdate();
					st.close();
					System.out.println("Insert mid priorty thread runned.");
				}
			
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	};midPriortyDbThread.start();
}

public void runInHighQueue() throws SQLException{
	
	Thread highPriortyDbThread = new Thread("Writing high priorty message to database") {
		Message msg ;
		PreparedStatement st;
		Connection connection = DriverManager.getConnection(url, user, password);
		public void run() {
			try {
				while (highPriQueue.size()>0)
				{
					msg = highPriQueue.poll();
					st = connection.prepareStatement("INSERT INTO \"highPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, msg.getId());
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					st.executeUpdate();
					st.close();
					System.out.println("Insert high priorty thread runned.");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	};highPriortyDbThread.start();
}

public void insertDBWithThread(Message msg) throws SQLException {

	switch (msg.getPriorty()) {
	case "Dusuk": 
		lowPriQueue.add(msg);
		break;
	case "Normal":
		midPriQueue.add(msg);
		break;
	case "Yuksek":
		highPriQueue.add(msg);
		break;
	default:
		lowPriQueue.add(msg);
		break;
	}

	runInHighQueue();
	runInMidQueue();
	runInLowQue();
}


public void deleteFromDB() {
	//TODO Delete message from database.
}
	
}




