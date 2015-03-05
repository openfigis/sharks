package org.sharks.dao.msaccess.config;

import org.sharks.dao.table.refArea;

public class MsAccessConfiguration {

	private Class<?>[] tables = { refArea.class };

	// private String dbLocation = "../sharks-db/Sharks.accdb";
	private String dbLocation = "../sharks-db/experiment.accdb";

	// private String dbLocation = "C:/Users/vaningen/git/sharks/sharks-server/sharks-db/Sharks.accdb";

	public Class<?>[] getTables() {
		return tables;
	}

	public String getDbLocation() {
		return dbLocation;
	}

}
