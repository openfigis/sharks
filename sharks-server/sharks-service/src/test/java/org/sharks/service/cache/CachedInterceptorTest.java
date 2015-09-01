/**
 * 
 */
package org.sharks.service.cache;

import static org.junit.Assert.*;

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
		when(manager.getOrCreateCache("myservice", "mycache")).thenReturn(new InMemoryCache<Object, Object>());
		return manager;
	}

	@Test
	public void test() {
		int value = service.get("mykey");
		verify(manager).getOrCreateCache("myservice", "mycache");
		assertEquals(0, value);
		
		value = service.get("mykey");
		assertEquals(0, value);
		
		value = service.get("myOtherKey");
		assertEquals(1, value);
	}
	
	@Service(name="myservice",type=ServiceType.INTERNAL)
	public static class MyService {
		
		int counter = 0;
		
		@Cached("mycache")
		public int get(String key) {
			return counter++;
		}
	}

}
