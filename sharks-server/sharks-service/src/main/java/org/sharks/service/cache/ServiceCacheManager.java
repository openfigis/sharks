/**
 * 
 */
package org.sharks.service.cache;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ServiceCacheManager {
	
	public <K,V> ServiceCache<K, V> getOrCreateCache(String serviceName, String name);
	
	public void clearCaches(String ... services);
	
	public void flushCaches(String ... services);

}
