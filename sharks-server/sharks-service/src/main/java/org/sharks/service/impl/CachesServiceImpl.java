/**
 * 
 */
package org.sharks.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);
	
	@Inject
	private CachesWarmer warmer;
	
	@Inject
	private ServiceCacheManager manager;
	
	@Inject
	private Configuration configuration;
	
	private ClearCacheStatus cleaningStatus = ClearCacheStatus.IDLE;
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup) {
		warmer.warmupCaches();
	}
	
	@Override
	public ClearCacheStatus clearCaches(String passphrase) {
		
		if (configuration.getCacheCleaningPassphrase()!=null 
				&& !configuration.getCacheCleaningPassphrase().isEmpty()
				&& !configuration.getCacheCleaningPassphrase().equals(passphrase)) {
			log.warn("Attempt to clean the cache with a wrong passphrase {}", passphrase);
			
			throw new WrongPasswordException();
		}
		
		synchronized (cleaningStatus) {
			if (cleaningStatus != ClearCacheStatus.ONGOING) asyncClearCaches();
		}
		
		return cleaningStatus;
	}
	
	private void asyncClearCaches() {
		
		log.info("submitting cache cleaning");
		cleaningStatus = ClearCacheStatus.ONGOING;
		
		executor.submit(new Runnable() {
			
			@Override
			public void run() {
				clearCaches();
				cleaningStatus = ClearCacheStatus.IDLE;
			}
		});
	}
	
	private void clearCaches() {
		log.info("cleaning all the caches");
		manager.clearAllCaches();
		warmer.warmupCaches();
		log.info("cache cleaning complete");
	}

	@Override
	public ClearCacheStatus getClearCacheStatus() {
		return cleaningStatus;
	}
	

}
