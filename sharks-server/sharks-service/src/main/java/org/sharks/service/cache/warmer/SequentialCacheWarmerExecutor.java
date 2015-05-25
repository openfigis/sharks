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
public class SequentialCacheWarmerExecutor implements CacheWarmingExecutor {

	@Override
	public void warm(WarmCache warmFunction) {
		warmFunction.warm();		
	}

	@Override
	public void waitCompletion() {
	}

}
