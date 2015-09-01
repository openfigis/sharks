package org.sharks.service.cache;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ServiceCacheProducer.class})
@Service(name="myTest",type=ServiceType.INTERNAL)
public class ServiceCacheProducerTest {
	
	@Inject @CacheName("myCache")
	ServiceCache<Long, String> namedCache;
	
	@Inject
	ServiceCacheManager manager;
	
	@Produces @Singleton
	private ServiceCacheManager setupServiceCacheManager() {
		ServiceCacheManager manager = Mockito.mock(ServiceCacheManager.class);

		return manager;
	}

	@Test
	public void test() {
		verify(manager, times(1)).getOrCreateCache(getService("myTest"),"myCache");
	}

}
