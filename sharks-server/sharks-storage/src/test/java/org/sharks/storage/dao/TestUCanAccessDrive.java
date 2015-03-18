/**
 * 
 */
package org.sharks.storage.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestUCanAccessDrive {
	
	private static String dbLocation;
	
	@BeforeClass
	public static void driverSetup() throws ClassNotFoundException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		dbLocation = TestUCanAccessDrive.class.getResource("/testdb.accdb").getPath();
	}

	@Test
	public void testOpenMultipleConnection() throws SQLException {
		
		List<Connection> connections = new ArrayList<Connection>();
		
		try {
			//open connections
			for (int i = 0; i<10; i++) {
			Connection connection = DriverManager.getConnection("jdbc:ucanaccess://"+dbLocation);
				connections.add(connection);
			}
			
			assertEquals(10, connections.size());
			
			//test connections
			for (Connection connection:connections) {
				assertTrue(connection.createStatement().executeQuery("SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS").next());
			}

		} finally {
			
			//close connections
			for (Connection connection:connections) {
				try {
				connection.close();
				} catch(Exception e){}
			}
		}

	}

}
