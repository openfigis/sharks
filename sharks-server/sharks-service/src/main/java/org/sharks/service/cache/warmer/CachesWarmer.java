/**
 * 
 */
package org.sharks.service.cache.warmer;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.cache.warmer.CacheWarmer.HighPriority;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class CachesWarmer {
	
	@Inject @HighPriority
	private Instance<CacheWarmer> highPriorityWarmers;

	@Inject
	private Instance<CacheWarmer> warmers;
	
	@Inject
	private CacheWarmingExecutor executor;
	
	public void warmupCaches() {
		for (CacheWarmer warmer:highPriorityWarmers) executor.warm(()->warmer.warmup());
		executor.waitCompletion();
		
		for (CacheWarmer warmer:warmers) executor.warm(()->warmer.warmup());
		executor.waitCompletion();
	}
	
}
