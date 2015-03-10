package org.sharks.dao.msaccess.config;

import org.sharks.dao.table.refSpecies;

public class MsAccessConfiguration {

	private Class<?>[] tables = { refSpecies.class };

	private String dbLocation = "../sharks-db/Sharks.accdb";

	public Class<?>[] getTables() {
		return tables;
	}

	public String getDbLocation() {
		return dbLocation;
	}

}
