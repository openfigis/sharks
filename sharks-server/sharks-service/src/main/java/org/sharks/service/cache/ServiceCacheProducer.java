/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class ServiceCacheProducer {
	
	@Produces
	public <K,V> ServiceCache<K, V> getCache(InjectionPoint injectionPoint, ServiceCacheManager cacheManager) {
		
		CacheName cacheNameAnnotation = injectionPoint.getAnnotated().getAnnotation(CacheName.class);
		String cacheName = cacheNameAnnotation!=null?cacheNameAnnotation.value():null;
		
		return cacheManager.getOrCreateCache(cacheName);
	}

}
