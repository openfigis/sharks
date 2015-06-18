/**
 * 
 */
package org.sharks.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

import javax.enterprise.inject.Alternative;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative
public class ConfigurationImpl implements Configuration {
	
	public static final String DB_FILE_LOCATION = "storage.dbfile";
	public static final String CACHE_CONFIG = "cache.config";
	public static final String CACHE_WARMUP = "cache.warmup";
	public static final String CACHE_CLEAN_PASSPHRASE = "cache.cleanPassphrase";
	public static final String CACHE_REFRESH_DELAY = "cache.refreshDelay";
	public static final String SHARKS_URL = "service.sharks";
	public static final String REFPUB_URL = "service.refpub";
	public static final String MONIKERS_URL = "service.monikers";
	public static final String GEOSERVER_SPECIES_LIST_URL = "service.geoserver.specieslist";
	public static final String SOLR_URL = "service.solr";
	
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
	
	@Override
	public String getDbFileLocation() {
		return properties.getProperty(DB_FILE_LOCATION);
	}
	
	@Override
	public URL getSharksRestUrl() {
		String sharksRestUrlProperty = properties.getProperty(SHARKS_URL);
		if (sharksRestUrlProperty == null) return null;
		
		try {
			return new URL(sharksRestUrlProperty);
		} catch (Exception e) {
			throw new RuntimeException("Wrong Sharks URL value in configuration "+sharksRestUrlProperty, e);
		}
	}
	
	@Override
	public String getRefPubUrl() {
		return properties.getProperty(REFPUB_URL);
	}
	
	@Override
	public String getMonikersUrl() {
		return properties.getProperty(MONIKERS_URL);
	}

	@Override
	public String getSolrUrl() {
		return properties.getProperty(SOLR_URL);
	}

	@Override
	public String getCacheConfiguration() {
		return properties.getProperty(CACHE_CONFIG);
	}

	@Override
	public CacheWarmupType getCacheWarmupType() {
		String value = properties.getProperty(CACHE_WARMUP);
		if (value == null) throw new RuntimeException("Cache warmup option \""+CACHE_WARMUP+"\" not specified");
		try {
			return CacheWarmupType.valueOf(value.toUpperCase());
		} catch(Exception e) {
			throw new RuntimeException("Wrong \""+CACHE_WARMUP+"\" value \""+value+"\" expected one of "+Arrays.toString(CacheWarmupType.values()));
		}
	}

	@Override
	public String getCacheCleaningPassphrase() {
		return properties.getProperty(CACHE_CLEAN_PASSPHRASE);
	}

	@Override
	public String getSpeciesListUrl() {
		return properties.getProperty(GEOSERVER_SPECIES_LIST_URL);
	}

	@Override
	public String getCacheRefreshDelay() {
		return properties.getProperty(CACHE_REFRESH_DELAY);
	}

}
