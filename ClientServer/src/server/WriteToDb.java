package server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import message.Message;

public class WriteToDb {
private final String url = "jdbc:postgresql://localhost/mesdb";
private final String user = "postgres";
private final String password = "test123";
private Connection connection;

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

public void insertDb(Message msg) {
	//TODO Insert message to database table according to priorty;
}


}
