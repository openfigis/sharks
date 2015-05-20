/**
 * 
 */
package org.sharks.service.cache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ServiceCacheManager {
	
	public <K,V> ServiceCache<K, V> getOrCreateCache(String name);
	
	public void clearAllCaches();

}
