package org.sharks.config;

import java.net.URL;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface Configuration {

	public String getDbFileLocation();
	
	public String getCacheConfiguration();
	
	public boolean isCacheWarmupEnabled();

	public String getRefPubUrl();

	public String getMonikersUrl();
	
	public String getSolrUrl();

	public URL getSharksRestUrl();

}