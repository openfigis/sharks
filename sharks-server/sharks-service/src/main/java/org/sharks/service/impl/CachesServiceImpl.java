/**
 * 
 */
package org.sharks.service.impl;

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
import org.sharks.service.event.ApplicationEvent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton @Slf4j
public class CachesServiceImpl implements CacheService {
	
	@Inject
	private ServiceCacheManager cacheManager;
	
	@Inject
	private ServiceCacheLifetimeManager lifetimeManager;
	
	@Inject
	private CachesWarmer cachesWarmer;
	
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
		log.info("loading caches");
		log.trace("cleaning internal caches");
		cacheManager.clearCaches(ServiceType.INTERNAL);
		log.trace("running cache warmers");
		cachesWarmer.warmupCaches();
		log.trace("flushing external caches");
		cacheManager.flushCaches(ServiceType.EXTERNAL);
		log.info("cache loading complete");
	}
	
	@Override
	public void clearCaches(String ... services) {
		cacheManager.clearCaches(services);
	}

	@Override
	public void flushCaches(String ... services) {
		cacheManager.clearCaches(services);
	}

}
