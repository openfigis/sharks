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
import java.util.concurrent.TimeUnit;

import javax.enterprise.inject.Alternative;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative
public class ConfigurationImpl implements Configuration {
	
	public static final String DB_FILE_LOCATION = "storage.dbfile";
	public static final String CACHE_CLEAN_PASSPHRASE = "cache.cleanPassphrase";
	public static final String CACHE_WARMUP = "cache.warmup";
	public static final String CACHE_LOCATION = "cache.location";
	public static final String SHARKS_REST_URL = "sharks.rest.url";
	public static final String SHARKS_CLIENT_URL = "sharks.client.url";
	public static final String REFPUB_URL = "refpub.url";
	public static final String REFPUB_CACHE_EXPIRATION = "refpub.cacheExpiration";
	public static final String MONIKERS_URL = "monikers.url";
	public static final String MONIKERS_CACHE_EXPIRATION = "monikers.cacheExpiration";
	public static final String GEOSERVER_SPECIES_LIST_URL = "geoserver.specieslist.url";
	public static final String GEOSERVER_CACHE_EXPIRATION = "geoserver.cacheExpiration";
	public static final String CITES_PARTIES_URL = "cites.parties.url";
	public static final String CITES_CACHE_EXPIRATION = "cites.cacheExpiration";
	public static final String CMS_PARTIES_URL = "cms.parties.url";
	public static final String CMS_CACHE_EXPIRATION = "cms.cacheExpiration";
	public static final String KOR_RESOURCES_URL = "kor.resources.url";
	public static final String KOR_CACHE_EXPIRATION = "kor.cacheExpiration";
	public static final String SOLR_URL = "solr.url";
	
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
		return getUrl(SHARKS_REST_URL);
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
	public String getCacheCleaningPassphrase() {
		return properties.getProperty(CACHE_CLEAN_PASSPHRASE);
	}
	
	@Override
	public CacheWarmupType getCacheWarmupType() {
		String value = properties.getProperty(CACHE_WARMUP);
		if (value == null) return CacheWarmupType.PARALLEL;
		try {
			return CacheWarmupType.valueOf(value.toUpperCase());
		} catch(Exception e) {
			throw new RuntimeException("Wrong \""+CACHE_WARMUP+"\" value \""+value+"\" expected one of "+Arrays.toString(CacheWarmupType.values()));
		}
	}

	@Override
	public String getGeoServerSpeciesListUrl() {
		return properties.getProperty(GEOSERVER_SPECIES_LIST_URL);
	}

	@Override
	public URL getSharksClientUrl() {
		return getUrl(SHARKS_CLIENT_URL);
	}
	
	private URL getUrl(String propertyName) {
		String urlValue = properties.getProperty(propertyName);
		if (urlValue == null) return null;
		
		try {
			return new URL(urlValue);
		} catch (Exception e) {
			throw new RuntimeException("Wrong URL value for property "+propertyName, e);
		}
	}

	@Override
	public String getCitesPartiesUrl() {
		return properties.getProperty(CITES_PARTIES_URL);
	}

	@Override
	public String getCacheLocation() {
		return properties.getProperty(CACHE_LOCATION);
	}

	@Override
	public Time getRefPubCacheExpiration() {
		return getTime(REFPUB_CACHE_EXPIRATION);
	}

	@Override
	public Time getMonikersCacheExpiration() {
		return getTime(MONIKERS_CACHE_EXPIRATION);
	}

	@Override
	public Time getGeoServerCacheExpiration() {
		return getTime(GEOSERVER_CACHE_EXPIRATION);
	}

	@Override
	public Time getCitesExpiration() {
		return getTime(CITES_CACHE_EXPIRATION);
	}
	
	private Time getTime(String propertyName) {
		String timeValue = properties.getProperty(propertyName);
		if (timeValue == null) return null;
		
		String[] tokens = timeValue.split(" ");
		if (tokens.length!=2) throw new IllegalArgumentException("Wrong time value for property "+propertyName+", expected value and time unit separate by a space");
		
		String valueToken = tokens[0];
		String unitToken = tokens[1];
		
		long value = -1;
		try {
			value = Long.parseLong(valueToken);
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid numeric value \""+valueToken+"\" for property "+propertyName, e);
		}
		
		TimeUnit unit = null;
		try {
			unit = TimeUnit.valueOf(unitToken.toUpperCase().trim());
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid time unit value \""+unitToken+"\" for property "+propertyName+" accepted values are "+Arrays.toString(TimeUnit.values()), e);
		}
		
		return new Time(value, unit);
	}

	@Override
	public String getCmsPartiesUrl() {
		return properties.getProperty(CMS_PARTIES_URL);
	}

	@Override
	public Time getCmsExpiration() {
		return getTime(CMS_CACHE_EXPIRATION);
	}

	@Override
	public String getKorResourcesUrl() {
		return properties.getProperty(KOR_RESOURCES_URL);
	}

	@Override
	public Time getKorExpiration() {
		return getTime(KOR_CACHE_EXPIRATION);
	}

}
