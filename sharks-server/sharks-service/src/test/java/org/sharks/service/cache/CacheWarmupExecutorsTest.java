/**
 * 
 */
package org.sharks.service.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.sharks.service.cache.warmer.CacheWarmingExecutor;
import org.sharks.service.cache.warmer.NopCacheWarmingExecutor;
import org.sharks.service.cache.warmer.SequentialCacheWarmerExecutor;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CacheWarmupExecutorsTest {

	@Test
	public void testNop() {
		CacheWarmingExecutor nop = new NopCacheWarmingExecutor();
		
		AtomicBoolean executed = new AtomicBoolean(false);
		nop.warm(()->executed.set(true));
		nop.waitCompletion();
		
		assertFalse(executed.get());
	}

	@Test
	public void testSequential() {
		CacheWarmingExecutor sequential = new SequentialCacheWarmerExecutor();
		
		AtomicInteger firstExecution = new AtomicInteger(0);
		AtomicInteger secondExecution = new AtomicInteger(0);
		sequential.warm(()->firstExecution.set(1));
		sequential.warm(()->secondExecution.set(firstExecution.get() + 1));
		sequential.waitCompletion();
		
		assertEquals(1, firstExecution.get());
		assertEquals(2, secondExecution.get());
	}

}
