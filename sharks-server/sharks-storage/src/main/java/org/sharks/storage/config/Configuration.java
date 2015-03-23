/**
 * 
 */
package org.sharks.storage.config;

import java.io.File;

/**
 * TODO should we move this class to a separate project?
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class Configuration {
	
	private static String ENV_PROPERTY = "SHARKS_DB";
	private static String DEV_DB_LOCATION = "../sharks-db/Sharks.accdb";
	private static String DEV_DB_LOCATION2 = "db/Sharks.accdb";
	
	public String getDbFileLocation() {
		System.out.println("current location "+(new File(".").getAbsolutePath()));
		String dbLocation = System.getenv(ENV_PROPERTY);
		//FIXME tmp workaround until we take a decision about configuration
		if (dbLocation == null && (new File(DEV_DB_LOCATION)).exists()) dbLocation = DEV_DB_LOCATION;
		if (dbLocation == null && (new File(DEV_DB_LOCATION2)).exists()) dbLocation = DEV_DB_LOCATION2;
		return dbLocation;
	}
	
	
}
