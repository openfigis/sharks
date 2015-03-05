package org.sharks.dao.msaccess.config;

import java.sql.Connection;

import javax.enterprise.inject.Any;

import org.sharks.dao.SharksException;

/**
 * MsAccessConnectionProducerTest
 * 
 * 
 * 
 * @author Erik van Ingen
 *
 */

/*
 * MsAccessConnectionProducerTest needs the @Any here
 */
@Any
public class SharksConnection {

	private boolean inUse = false;
	private Connection connection;

	synchronized public Connection getConnection() {
		if (inUse) {
			throw new SharksException("Connection is already in use");
		}
		inUse = true;
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public boolean isInUse() {
		return inUse;
	}

	synchronized public void release() {
		if (!inUse) {
			throw new SharksException("Connection was not in use, so no need to release it");
		}
		inUse = false;
	}

}
