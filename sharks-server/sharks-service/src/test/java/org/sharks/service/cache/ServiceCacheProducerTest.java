package org.sharks.service.cache;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestUtils.getService;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ ServiceCacheProducer.class })
@Service(name = "myTest", type = ServiceType.INTERNAL)
public class ServiceCacheProducerTest {

	static String cache = "myCache";

	@Inject
	@CacheName("myCache")
	ServiceCache<Long, String> namedCache;

	@Inject
	ServiceCacheManager manager;

	@Produces
	@Singleton
	private ServiceCacheManager setupServiceCacheManager() {
		ServiceCacheManager manager = Mockito.mock(ServiceCacheManager.class);
		when(manager.getOrCreateCache(new ServiceInfo("myTest", ServiceType.INTERNAL), "myCache"))
				.thenReturn(new InMemoryCache<Object, Object>());
		return manager;
	}

	@Test
	public void test() {
		assertNotNull(namedCache);
		verify(manager, times(1)).getOrCreateCache(getService("myTest"), "myCache");
	}

}
