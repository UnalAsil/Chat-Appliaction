package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import message.Message;

public class WriteToDb {
private final String url = "jdbc:postgresql://localhost/mesdb";
private final String user = "postgres";
private final String password = "test123";
private Connection connection;
private static int IdLowPri=2;
private static int IdMidPri=2;
private static int IdHighPri=2;


public WriteToDb() throws SQLException {
	this.connection = DriverManager.getConnection(url, user, password);
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

public void insertDb(Message msg) throws SQLException {
	//TODO Insert message to database table according to priority;
	PreparedStatement st;
	switch (msg.getPriorty()) {
	case "Dusuk":  	st = this.connection.prepareStatement("INSERT INTO \"lowPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, IdLowPri++);
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());
					break;
				
	case "Normal": 	st = this.connection.prepareStatement("INSERT INTO \"midPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, IdMidPri++);
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());			
					break;
	case "Yuksek": 	st = this.connection.prepareStatement("INSERT INTO \"highPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, IdHighPri++);
					st.setString(2, msg.getTo());
					st.setString(3, msg.getCc());
					st.setString(4, msg.getSubject());
					st.setString(5, msg.getPriorty());
					st.setString(6, msg.getMessage());			
					break;
	default      :	st = this.connection.prepareStatement("INSERT INTO \"lowPriorty\" (\"id\", \"Tom\", \"Cc\", \"Subject\", \"Type\", \"Message\") VALUES (?, ?, ?, ?, ?, ?)");
					st.setInt(1, IdLowPri++);
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
	
}



