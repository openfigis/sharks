/**
 * 
 */
package org.sharks.service.cache.warmer;

import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Alternative @Singleton
public class NopCacheWarmingExecutor implements CacheWarmingExecutor {

	@Override
	public void warm(WarmCache warmFunction) {
	}

	@Override
	public void waitCompletion() {
	}

}
