/**
 * 
 */
package org.sharks.service.cache.warmer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Alternative @Singleton
public class ParallelCacheWarmingExecutor implements CacheWarmingExecutor {
	
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
	private List<Future<Void>> warmers = new ArrayList<Future<Void>>();

	@Override
	public void warm(WarmCache warmFunction) {
		Future<Void> future = executor.submit(new Callable<Void>() {

			@Override
			public Void call() throws Exception {
				warmFunction.warm();
				return null;
			}
		});
		
		warmers.add(future);
		
	}

	@Override
	public void waitCompletion() {
		for (Future<Void> future:warmers) {
			try {
			future.get();
			} catch(Exception e) {
				log.error("Error waiting for warmer", e);
			}
		}
		
		warmers.clear();
	}

}
