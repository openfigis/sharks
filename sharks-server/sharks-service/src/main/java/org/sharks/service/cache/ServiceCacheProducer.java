/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

import org.sharks.config.Configuration;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Slf4j
public class ServiceCacheProducer {
	
	@Produces
	public <K,V> ServiceCache<K, V> getCache(InjectionPoint injectionPoint, CacheManager cacheManager) {
		
		CacheName cacheNameAnnotation = injectionPoint.getAnnotated().getAnnotation(CacheName.class);
		String cacheName = cacheNameAnnotation!=null?cacheNameAnnotation.value():null;
		
		if (cacheName!=null) {
			if (!cacheManager.cacheExists(cacheName)) cacheManager.addCache(cacheName);
			return new ServiceEhCache<K, V>(cacheManager.getCache(cacheName));
		}
		
		return new InMemoryCache<K, V>();
	}
	

	@Produces @Singleton
	public CacheManager getCacheManager(Configuration configuration) {

		String cacheConfigurationFile = configuration.getCacheConfiguration();

		if (cacheConfigurationFile == null || cacheConfigurationFile.isEmpty()) {
			log.info("cache configuration file not specified, using default one");
			return CacheManager.getInstance();
		} 

		log.info("loading cache configuration from "+cacheConfigurationFile);
		return CacheManager.newInstance(cacheConfigurationFile);
	}

}
