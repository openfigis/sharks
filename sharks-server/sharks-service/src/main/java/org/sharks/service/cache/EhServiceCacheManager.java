/**
 * 
 */
package org.sharks.service.cache;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;

import org.sharks.config.Configuration;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class EhServiceCacheManager implements ServiceCacheManager {
	
	@Inject
	private CacheManager cacheManager; 

	@Override
	public <K, V> ServiceCache<K, V> getOrCreateCache(String cacheName) {
		
		if (cacheName!=null) {
			if (!cacheManager.cacheExists(cacheName)) cacheManager.addCache(cacheName);
			return new EhServiceCache<K, V>(cacheManager.getCache(cacheName));
		}
		
		return new InMemoryCache<K, V>();
	}

	@Override
	public void clearAllCaches() {
		cacheManager.clearAll();
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
	
	
	public void shutdownCacheManager(@Disposes CacheManager cacheManager) {
		log.info("shutting down the cache manager");
		cacheManager.shutdown();
	}

}
