/**
 * 
 */
package org.sharks.service.impl;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.CacheService;
import org.sharks.service.cache.ServiceCacheManager;
import org.sharks.service.cache.warmer.CachesWarmer;
import org.sharks.service.event.ApplicationEvent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton @Slf4j
public class CachesServiceImpl implements CacheService {
	
	@Inject
	private CachesWarmer warmer;
	
	@Inject
	private ServiceCacheManager manager;
	
	@Inject
	private Configuration configuration;
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup) {
		warmer.warmupCaches();
	}
	
	@Override
	public boolean clearCaches(String passphrase) {
		if (configuration.getCacheCleaningPassphrase()!=null 
				&& !configuration.getCacheCleaningPassphrase().isEmpty()
				&& !configuration.getCacheCleaningPassphrase().equals(passphrase)) {
			log.warn("Attempt to clean the cache with a wrong passphrase {}", passphrase);
			
			return false;
		}
		
		log.info("cleaning all the caches");
		manager.clearAllCaches();
		warmer.warmupCaches();
		
		return true;
	}
	

}
