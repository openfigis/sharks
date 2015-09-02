/**
 * 
 */
package org.sharks.service.cache.warmer;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton
public class CachesWarmer {
	
	@Inject
	private Instance<CacheWarmer> warmers;
	
	@Inject
	private CacheWarmingExecutor executor;
	
	public void warmupCaches() {
		for (CacheWarmer warmer:warmers) {
			log.trace(warmer.getClass().getSimpleName()+" started warming...");
			executor.warm(()->warmer.warmup());
		}
		log.trace("waiting for warmup completion");
		executor.waitCompletion();
		log.trace("warmup complete");
	}
	
}
