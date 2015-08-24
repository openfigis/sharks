/**
 * 
 */
package org.sharks.service.geoserver;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.cache.warmer.CacheWarmer.HighPriority;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton @HighPriority
public class GeoServerCacheWarmer implements CacheWarmer {
	
	@Inject
	private GeoServerService geoServerService;

	@Override
	public void warmup() {
		log.info("warming cache");
		
		geoServerService.hasSpeciesDistributionMap("ANY");
		
		log.trace("cache warmup complete");
	}

}
