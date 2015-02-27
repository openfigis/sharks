package org.sharks.dao.msaccess.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import net.ucanaccess.jdbc.UcanaccessDriver;

import org.sharks.dao.SharksException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsAccessConnectionProvider {

	public static String dbLocation = "../sharks-db/Sharks.accdb";

	final static protected Logger LOG = LoggerFactory.getLogger(MsAccessConnectionProvider.class);

	final static protected String UCANACCESS_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";

	final static protected String UCANACCESS_URL = "jdbc:ucanaccess://";

	protected Connection connection;

	public MsAccessConnectionProvider() {
		File file = new File(dbLocation);
		if (!file.exists()) {
			throw new SharksException("No msaccess db found on location " + dbLocation);
		}

	}

	@PostConstruct
	final public void postConstruct() {
		try {

			Class.forName(UCANACCESS_DRIVER);

			String url = UcanaccessDriver.URL_PREFIX + dbLocation + ";newDatabaseVersion=V2003";

			DriverManager.getConnection(url);
			// DriverManager.getConnection(url, "sa", "");

			this.connection = DriverManager.getConnection(url, "", "");
			LOG.info("Microsoft Access file : " + dbLocation + "successfully connected!");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SharksException(e);
		} catch (ClassNotFoundException e) {
			throw new SharksException(e);
		}
	}

	final public Connection getConnection() {
		return connection;
	}

}
