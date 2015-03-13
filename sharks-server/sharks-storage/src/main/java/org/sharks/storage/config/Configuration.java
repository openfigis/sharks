/**
 * 
 */
package org.sharks.storage.config;

/**
 * TODO should we move this class to a separate project?
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class Configuration {
	
	private static String ENV_PROPERTY = "SHARKS_DB";
	private static String DEV_DB_LOCATION = "../sharks-db/Sharks.accdb";
	
	public String getDbFileLocation() {
		String dbLocation = System.getenv(ENV_PROPERTY);
		if (dbLocation == null) dbLocation = DEV_DB_LOCATION;
		return dbLocation;
	}
	
	
}
