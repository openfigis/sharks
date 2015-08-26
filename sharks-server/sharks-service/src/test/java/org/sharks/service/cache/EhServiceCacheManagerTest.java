package org.sharks.service.cache;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.sharks.service.test.util.TestUtils.*;
import net.sf.ehcache.Ehcache;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sharks.config.Configuration;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class EhServiceCacheManagerTest {

	private static EhServiceCacheManager manager;
	
	@BeforeClass
	public static void setupManager() {
		
		Configuration configuration = Mockito.mock(Configuration.class);
		when(configuration.getCacheConfiguration()).thenReturn(getResourcePath("/test_cache.xml"));
		
		manager = new EhServiceCacheManager(configuration);
	}
	
	
	@Test
	public void testGetOrCreateCache() {
		ServiceCache<String, String> ehcache = manager.getOrCreateCache("ehcache");
		assertNotNull(ehcache);
		
		ServiceCache<String, String> inmemorycache = manager.getOrCreateCache("inmemorycache");
		assertNotNull(inmemorycache);
		
		assertNotEquals(reveal(ehcache).hashCode(), reveal(inmemorycache).hashCode());
	}
	
	@Test
	public void testGetOrCreateCacheSameCache() {
		ServiceCache<String, String> ehcache = manager.getOrCreateCache("ehcache");
		ServiceCache<String, String> ehcache2 = manager.getOrCreateCache("ehcache");
		assertEquals(reveal(ehcache).hashCode(), reveal(ehcache2).hashCode());
		
		ServiceCache<String, String> inmemorycache = manager.getOrCreateCache("inmemorycache");
		ServiceCache<String, String> inmemorycache2 = manager.getOrCreateCache("inmemorycache");
		assertEquals(reveal(inmemorycache).hashCode(), reveal(inmemorycache2).hashCode());
	}

	@Test
	public void testClearAllCaches() {
		ServiceCache<String, String> ehcache = manager.getOrCreateCache("ehcache");
		ehcache.put("key", "value");
		
		ServiceCache<String, String> inmemorycache = manager.getOrCreateCache("inmemorycache");
		inmemorycache.put("key", "value");
		
		manager.clearAllCaches();
		assertEquals(0, ehcache.size());
		assertEquals(0, inmemorycache.size());
	}
	
	private static Ehcache reveal(ServiceCache<?,?> cache) {
		return ((EhServiceCache<?,?>)cache).cache;
	}
	

}
