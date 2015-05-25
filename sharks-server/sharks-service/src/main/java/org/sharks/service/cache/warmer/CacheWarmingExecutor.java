/**
 * 
 */
package org.sharks.service.cache.warmer;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheWarmingExecutor {
	
	public void warm(WarmCache warmFunction);
	
	public void waitCompletion();
	
	
	@FunctionalInterface
	public static interface WarmCache {
		void warm();
	}

}
