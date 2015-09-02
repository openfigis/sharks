/**
 * 
 */
package org.sharks.service.cache;

import lombok.Data;

import org.sharks.service.Service.ServiceType;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ServiceCacheManager {
	
	public <K,V> ServiceCache<K, V> getOrCreateCache(ServiceInfo service, String name);
	
	public void clearCaches(String ... services);
	
	public void flushCaches(String ... services);
	
	public void clearCaches(ServiceType ... types);
	public void flushCaches(ServiceType ... types);
	
	@Data
	public static class ServiceInfo {
		private final String name;
		private final ServiceType type;
	}

}
