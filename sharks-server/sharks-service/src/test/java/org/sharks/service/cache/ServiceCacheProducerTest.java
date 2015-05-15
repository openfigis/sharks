package org.sharks.service.cache;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.config.Configuration;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ServiceCacheProducer.class, InMemoryCache.class})
public class ServiceCacheProducerTest {
	
	@Inject
	ServiceCache<String, String> cache;
	
	@Inject @CacheName("myCache")
	ServiceCache<Long, String> namedCache;
	
	@Inject @CacheName("myCache")
	ServiceCache<Long, String> sameNamedCache;
	
	@Produces
	private Configuration setupConfiguration() {
		Configuration conf = Mockito.mock(Configuration.class);
		
		when(conf.getCacheConfiguration()).thenReturn(null);
		
		return conf;
	}

	@Test
	public void test() {
		assertNotNull(cache);
		
		assertNotNull(namedCache);
		assertNotNull(sameNamedCache);
		
		namedCache.put(0l, "TEST");
		assertEquals("TEST", sameNamedCache.get(0l).getValue());
	}

}
