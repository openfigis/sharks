/**
 * 
 */
package org.sharks.service.impl;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
	
	private final ExecutorService cleanExecutor = Executors.newFixedThreadPool(1);
    private final ScheduledExecutorService cacheRefreshScheduler = Executors.newScheduledThreadPool(1);
	
	@Inject
	private CachesWarmer warmer;
	
	@Inject
	private ServiceCacheManager manager;
	
	@Inject
	private Configuration configuration;
	
	private ClearCacheStatus cleaningStatus = ClearCacheStatus.IDLE;
	
	void setupCacheRefresh(@Observes ApplicationEvent.Startup startup, Configuration configuration) {
		log.trace("setting up cache auto-refresh");
		String refreshDelay = configuration.getCacheRefreshDelay();
		if (refreshDelay!=null && !refreshDelay.isEmpty()) {
			long delayMs = -1;
			try {
				delayMs = parseRefreshDelay(refreshDelay);
			} catch(Exception e) {
				log.error("Failed parsing refresh delay parameter: "+refreshDelay, e);
				return;
			}
			
			log.info("scheduling cache auto-refresh to "+delayMs+" ms");
			
			cacheRefreshScheduler.scheduleWithFixedDelay(new Runnable() {
				
				@Override
				public void run() {
					log.trace("cache auto-refreshing submitting task...");
					asyncClearCaches();
				}
			}, delayMs, delayMs, TimeUnit.MILLISECONDS);
			
		} else log.trace("Auto-refresh disabled");
	}
	
	private long parseRefreshDelay(String param) {
		String[] tokens = param.split(" ");
		if (tokens.length!=2) throw new IllegalArgumentException("Expected value and time unit separate by a space");
		String valueToken = tokens[0];
		String unitToken = tokens[1];
		
		long value = -1;
		try {
			value = Long.parseLong(valueToken);
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid numeric value found: "+valueToken, e);
		}
		
		TimeUnit unit = null;
		try {
			unit = TimeUnit.valueOf(unitToken.toUpperCase().trim());
		} catch(Exception e) {
			throw new IllegalArgumentException("Invalid time unit value found: "+unitToken+" accepted values are "+Arrays.toString(TimeUnit.values()), e);
		}
		
		log.trace("refresh delay value: {} unit: {}", value, unit);
		
		return TimeUnit.MILLISECONDS.convert(value, unit);
	}
	
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
		
		asyncClearCaches();
		
		return cleaningStatus;
	}	
	
	private synchronized void asyncClearCaches() {
		
		if (!(cleaningStatus != ClearCacheStatus.ONGOING)) return;
		
		log.info("submitting cache cleaning");
		cleaningStatus = ClearCacheStatus.ONGOING;
		
		cleanExecutor.submit(new Runnable() {
			
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
