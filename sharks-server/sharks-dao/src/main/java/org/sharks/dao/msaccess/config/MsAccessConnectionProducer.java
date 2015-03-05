package org.sharks.dao.msaccess.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import net.ucanaccess.jdbc.UcanaccessDriver;

import org.sharks.dao.SharksException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsAccessConnectionProducer {

	final static private Logger LOG = LoggerFactory.getLogger(MsAccessConnectionProducer.class);

	final static private String UCANACCESS_DRIVER = "net.ucanaccess.jdbc.UcanaccessDriver";

	@Inject
	MsAccessConfiguration c;

	@Produces
	@ApplicationScoped
	final public SharksConnection getSharksConnection() {
		try {
			Class.forName(UCANACCESS_DRIVER);

			// String url = UcanaccessDriver.URL_PREFIX + c.getDbLocation() + ";newDatabaseVersion=V2003";
			String url = UcanaccessDriver.URL_PREFIX + c.getDbLocation();
			Connection connection = DriverManager.getConnection(url);
			LOG.info("Microsoft Access file : " + c.getDbLocation() + "successfully connected!");

			SharksConnection sharksConnection = new SharksConnection();
			sharksConnection.setConnection(connection);

			return sharksConnection;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SharksException(e);
		} catch (ClassNotFoundException e) {
			throw new SharksException(e);
		}

	}

}
