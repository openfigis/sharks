/**
 * 
 */
package org.sharks.service.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ServiceCacheProducer.class})
public class EhServiceCacheManagerTest {
	
	@Inject
	private EhServiceCacheManager manager;

	@Produces @Singleton
	private CacheManager setupCacheManager() {
		return CacheManager.newInstance();
	}
	
	int added = 0;
	int cleaned = 0;
	int flushed = 0;
	
	void cacheAdded(@Observes CacheEvent.CacheAdded event){
		added++;
	}
	
	void cacheCleaned(@Observes CacheEvent.CachesCleaned event){
		cleaned++;
	}
	
	void cacheFlushed(@Observes CacheEvent.CachesFlushed event){
		flushed++;
	}

	@Before
	public void cleanCounters() {
		added = 0;
		cleaned = 0;
		flushed = 0;
	}


	/**
	 * Test method for {@link org.sharks.service.cache.EhServiceCacheManager#getOrCreateCache(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetOrCreateCache() {
		ServiceCache<String, String> cache = manager.getOrCreateCache("myservice", "mycache");
		assertNotNull(cache);
		assertEquals(1, added);
	}
	
	@Test
	public void testGetOrCreateCacheDifferentServices() {
		ServiceCache<String, String> cache1 = manager.getOrCreateCache("myservice", "mycache");
		ServiceCache<String, String> cache2 = manager.getOrCreateCache("myotherservice", "mycache");
		
		assertNotEquals(reveal(cache1).hashCode(), reveal(cache2).hashCode());
		assertEquals(2, added);
	}
	
	@Test
	public void testGetOrCreateCacheSameServices() {
		ServiceCache<String, String> cache1 = manager.getOrCreateCache("myservice", "mycache");
		ServiceCache<String, String> cache2 = manager.getOrCreateCache("myservice", "mycache");
		
		assertEquals(reveal(cache1).hashCode(), reveal(cache2).hashCode());
		assertEquals(1, added);
	}

	/**
	 * Test method for {@link org.sharks.service.cache.EhServiceCacheManager#clearCaches(java.lang.String[])}.
	 */
	@Test
	public void testClearCaches() {
		ServiceCache<String, String> cache = manager.getOrCreateCache("myservice", "mycache");
		cache.put("mykey", "myvalue");
		assertTrue(cache.get("mykey").isPresent());
		
		manager.clearCaches("myservice");
		
		assertFalse(cache.get("mykey").isPresent());
		assertEquals(1, cleaned);
	}

	/**
	 * Test method for {@link org.sharks.service.cache.EhServiceCacheManager#flushCaches(java.lang.String[])}.
	 */
	@Test
	public void testFlushCaches() {
		ServiceCache<String, String> cache = manager.getOrCreateCache("myservice", "mycache");
		cache.put("mykey", "myvalue");
		assertTrue(cache.get("mykey").isPresent());
		
		manager.flushCaches("myservice");
		
		assertTrue(cache.get("mykey").isPresent());
		assertEquals(1, flushed);
	}

	private static Ehcache reveal(ServiceCache<?,?> cache) {
		return ((EhServiceCache<?,?>)cache).getCache();
	}

}
