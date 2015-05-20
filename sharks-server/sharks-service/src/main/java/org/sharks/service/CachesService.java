/**
 * 
 */
package org.sharks.service;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.cache.CachesWarmer;
import org.sharks.service.cache.ServiceCacheManager;
import org.sharks.service.event.ApplicationEvent;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class CachesService {
	
	@Inject
	private CachesWarmer warmer;
	
	@Inject
	private ServiceCacheManager manager;
	
	void cacheWarmup(@Observes ApplicationEvent.Startup startup) {
		warmer.warmupCaches();
	}
	
	public void clearCaches() {
		manager.clearAllCaches();
	}
	

}
