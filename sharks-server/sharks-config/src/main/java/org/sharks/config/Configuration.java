package org.sharks.config;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface Configuration {

	public String getDbFileLocation();
	
	public String getCacheCleaningPassphrase();
	
	public CacheWarmupType getCacheWarmupType();
	
	public String getCacheLocation();
	
	
	public URL getSharksRestUrl();
	
	public URL getSharksClientUrl();
	

	public String getRefPubUrl();
	public Time getRefPubCacheExpiration();

	public String getMonikersUrl();
	public Time getMonikersCacheExpiration();
	
	public String getGeoServerSpeciesListUrl();
	public Time getGeoServerCacheExpiration();
	
	public String getCitesPartiesUrl();
	public Time getCitesExpiration();
	
	public String getCmsPartiesUrl();
	public Time getCmsExpiration();
	
	
	public String getSolrUrl();
	
	public enum CacheWarmupType {
		NONE,
		SEQUENTIAL,
		PARALLEL;
	}
	
	@Data
	public class Time {
		private final long value;
		private final TimeUnit unit;
	}

}