/**
 * 
 */
package org.sharks.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.enterprise.inject.Alternative;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative
public class ConfigurationImpl implements Configuration {
	
	public static final String DB_FILE_LOCATION = "storage.dbfile";
	public static final String REFPUB_URL = "service.refpub";
	public static final String MONIKERS_URL = "service.monikers";
	
	private Properties properties;
	
	public ConfigurationImpl() {
		properties = new Properties();
	}
	
	public void load(String propertiesFileLocation) {
		File propertiesFile = new File(propertiesFileLocation);
		if (!propertiesFile.exists()) throw new IllegalStateException("Configuration file with path \""+propertiesFileLocation+"\" does not exists");
		
		try (InputStream is = new FileInputStream(propertiesFile)) {
			properties.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Failed reading configuration file "+propertiesFileLocation, e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.sharks.config.Configuration#getDbFileLocation()
	 */
	@Override
	public String getDbFileLocation() {
		return properties.getProperty(DB_FILE_LOCATION);
	}
	
	/* (non-Javadoc)
	 * @see org.sharks.config.Configuration#getRefPubUrl()
	 */
	@Override
	public String getRefPubUrl() {
		return properties.getProperty(REFPUB_URL);
	}
	
	/* (non-Javadoc)
	 * @see org.sharks.config.Configuration#getMonikersUrl()
	 */
	@Override
	public String getMonikersUrl() {
		return properties.getProperty(MONIKERS_URL);
	}

}
