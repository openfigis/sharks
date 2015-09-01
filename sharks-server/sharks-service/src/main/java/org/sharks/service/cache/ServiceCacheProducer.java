/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.sharks.service.Service;
import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class ServiceCacheProducer {
	
	@Produces
	public <K,V> ServiceCache<K, V> getCache(InjectionPoint injectionPoint, ServiceCacheManager cacheManager) {
		
		Service service = injectionPoint.getMember().getDeclaringClass().getAnnotation(Service.class);
		if (service == null) throw new IllegalArgumentException("The injection point "+injectionPoint+" is missing the "+Service.class.getSimpleName()+" annotation");
		
		CacheName cacheNameAnnotation = injectionPoint.getAnnotated().getAnnotation(CacheName.class);
		if (cacheNameAnnotation == null) throw new IllegalArgumentException("The injection point "+injectionPoint+" is missing the "+CacheName.class.getSimpleName()+" annotation");
				
		String cacheName = cacheNameAnnotation.value();

		return cacheManager.getOrCreateCache(new ServiceInfo(service.name(), service.type()), cacheName);
	}

}
