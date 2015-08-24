/**
 * 
 */
package org.sharks.service.cites;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cache.warmer.CacheWarmer;
import org.sharks.service.cache.warmer.CacheWarmer.HighPriority;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton @HighPriority
public class CitesCacheWarmer implements CacheWarmer {
	
	@Inject
	private CitesService citesService;

	@Override
	public void warmup() {
		log.info("warming cache");
		
		citesService.getParties();
		
		log.trace("cache warmup complete");
	}

}
