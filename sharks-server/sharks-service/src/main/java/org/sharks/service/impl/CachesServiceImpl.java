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
import org.sharks.config.Configuration.Time;
import org.sharks.service.CacheService;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.CacheEvent;
import org.sharks.service.cache.ServiceCacheLifetimeManager;
import org.sharks.service.cache.ServiceCacheManager;
import org.sharks.service.cache.warmer.CachesWarmer;
import org.sharks.service.dto.ClearCacheStatus;
import org.sharks.service.event.ApplicationEvent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton @Slf4j
public class CachesServiceImpl implements CacheService {
	
	private final ExecutorService cleanExecutor = Executors.newFixedThreadPool(1);
	
	@Inject
	private ServiceCacheManager cacheManager;
	
	@Inject
	private ServiceCacheLifetimeManager lifetimeManager;
	
	@Inject
	private CachesWarmer cachesWarmer;
	
	private ClearCacheStatus cleaningStatus = ClearCacheStatus.IDLE;
	
	void setupCacheExpirations(@Observes ApplicationEvent.Startup startup, Configuration configuration) {
		log.trace("setting up cache expirations");
		
		Time refpubCacheExpiration = configuration.getRefPubCacheExpiration();
		if (refpubCacheExpiration!=null) lifetimeManager.scheduleExpiration("refpub", refpubCacheExpiration);
		
		Time monikersCacheExpiration = configuration.getMonikersCacheExpiration();
		if (monikersCacheExpiration!=null) lifetimeManager.scheduleExpiration("monikers", monikersCacheExpiration);
		
		Time geoServerCacheExpiration = configuration.getGeoServerCacheExpiration();
		if (geoServerCacheExpiration!=null) lifetimeManager.scheduleExpiration("geoserver", geoServerCacheExpiration);
		
		Time citesCacheExpiration = configuration.getCitesExpiration();
		if (citesCacheExpiration!=null) lifetimeManager.scheduleExpiration("cites", citesCacheExpiration);
		
		log.trace("scheduling complete");
		
		loadCaches();
	}
	
	void cacheCleaned(@Observes CacheEvent.CachesCleaned event) {
		log.info("cleaned caches for the following services {}", event.getServices());
		boolean external = event.getServices().stream().anyMatch((service)->service.getType()==ServiceType.EXTERNAL);
		if (external) loadCaches();
	}
	
	private synchronized void loadCaches() {
		cleaningStatus = ClearCacheStatus.ONGOING;
		log.info("loading caches");
		
		log.trace("cleaning internal caches");
		cacheManager.clearCaches(ServiceType.INTERNAL);
		
		log.trace("running cache warmers");
		cachesWarmer.warmupCaches();
		
		log.trace("flushing external caches (free mem. {} bytes)",Runtime.getRuntime().freeMemory());
		cacheManager.flushCaches(ServiceType.EXTERNAL);
		log.trace("flush complete (free mem. {} bytes)",Runtime.getRuntime().freeMemory());
		
		cleaningStatus = ClearCacheStatus.IDLE;
		log.info("cache loading complete");
	}
	
	@Override
	public void clearCaches(String ... services) {
		cleaningStatus = ClearCacheStatus.ONGOING;
		cacheManager.clearCaches(services);
	}
	
	@Override
	public void clearExternalCaches() {
		cleaningStatus = ClearCacheStatus.ONGOING;
		cacheManager.clearCaches(ServiceType.EXTERNAL);
	}
	
	@Override
	public synchronized void asyncClearExternalCaches() {
		
		if (!(cleaningStatus != ClearCacheStatus.ONGOING)) return;
		
		log.info("submitting cache cleaning");
		cleaningStatus = ClearCacheStatus.ONGOING;
		
		cleanExecutor.submit(new Runnable() {
			
			@Override
			public void run() {
				clearExternalCaches();
			}
		});
	}
	

	@Override
	public void flushCaches(String ... services) {
		cacheManager.flushCaches(services);
	}
	
	@Override
	public ClearCacheStatus getClearCacheStatus() {
		return cleaningStatus;
	}

}
