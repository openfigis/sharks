/**
 * 
 */
package org.sharks.service.cache;

import javax.annotation.PreDestroy;
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
	
	private CacheManager cacheManager; 
	
	@Inject
	public EhServiceCacheManager(Configuration configuration) {
		String cacheConfigurationFile = configuration.getCacheConfiguration();

		if (cacheConfigurationFile == null || cacheConfigurationFile.isEmpty()) {
			log.info("cache configuration file not specified, using default one");
			cacheManager = CacheManager.getInstance();
		} else {

		log.info("loading cache configuration from "+cacheConfigurationFile);
		cacheManager = CacheManager.newInstance(cacheConfigurationFile);
		}
	}

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
	
	@PreDestroy
	private void shutdownCacheManager() {
		log.info("shutting down the cache manager");
		cacheManager.shutdown();
	}

}
