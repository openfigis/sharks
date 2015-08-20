package org.sharks.config;

import java.net.URL;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface Configuration {

	public String getDbFileLocation();
	
	public String getCacheConfiguration();
	
	public CacheWarmupType getCacheWarmupType();
	
	public String getCacheCleaningPassphrase();
	
	public String getCacheRefreshDelay();

	public String getRefPubUrl();

	public String getMonikersUrl();
	
	public String getSolrUrl();

	public URL getSharksRestUrl();
	
	public URL getSharksClientUrl();
	
	public String getSpeciesListUrl();
	
	public String getCitesPartiesUrl();
	
	public enum CacheWarmupType {
		NONE,
		SEQUENTIAL,
		PARALLEL;
	}

}