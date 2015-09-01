package org.sharks.service;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheService {
	
	public void clearCaches(String ... services);
	public void flushCaches(String ... services);

}