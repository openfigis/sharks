/**
 * 
 */
package org.sharks.service.cache;

import static org.junit.Assert.*;
import static org.sharks.service.test.util.TestUtils.getService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;

import static org.mockito.Mockito.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({CachedInterceptor.class})
public class CachedInterceptorTest {
	
	@Inject
	private MyService service;
	
	@Inject
	private ServiceCacheManager manager;
	
	@Produces @Singleton
	private ServiceCacheManager setupServiceCacheManager() {
		ServiceCacheManager manager = mock(ServiceCacheManager.class);
		when(manager.getOrCreateCache(getService("myservice"), "mycache")).thenReturn(new InMemoryCache<Object, Object>());
		when(manager.getOrCreateCache(getService("myservice"), "myOtherCache")).thenReturn(new InMemoryCache<Object, Object>());
		return manager;
	}

	@Test
	public void testWithKey() {
		int value = service.get("mykey");
		verify(manager).getOrCreateCache(getService("myservice"), "mycache");
		assertEquals(0, value);
		
		value = service.get("mykey");
		assertEquals(0, value);
		
		value = service.get("myOtherKey");
		assertEquals(1, value);
	}
	
	@Test
	public void testWithStaticKey() {
		int value = service.list();
		verify(manager).getOrCreateCache(getService("myservice"), "myOtherCache");
		assertEquals(0, value);
		
		value = service.list();
		assertEquals(0, value);
	}
	
	@Service(name="myservice",type=ServiceType.INTERNAL)
	public static class MyService {
		
		int getCounter = 0;
		int listCounter = 0;
		
		@Cached("mycache")
		public int get(String key) {
			return getCounter++;
		}
		
		@Cached(value="myOtherCache",staticKey="mykey")
		public int list() {
			return listCounter++;
		}
	}

}
