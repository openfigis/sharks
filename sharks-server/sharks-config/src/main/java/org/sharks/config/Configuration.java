package org.sharks.config;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface Configuration {

	public String getDbFileLocation();

	public String getRefPubUrl();

	public String getMonikersUrl();
	
	public String getSolrUrl();

}