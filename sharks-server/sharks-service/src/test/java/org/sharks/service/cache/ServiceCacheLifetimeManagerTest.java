package org.sharks.service.cache;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.concurrent.TimeUnit;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.config.Configuration.Time;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ServiceCacheProducer.class})
public class ServiceCacheLifetimeManagerTest {
	
	@Inject
	private ServiceCacheLifetimeManager manager;
	
	@Inject
	private ServiceCacheManager serviceCacheManager;
	
	@Produces @Singleton
	private ServiceCacheManager setupServiceCacheManager() {
		return mock(ServiceCacheManager.class);
	}


	@Test
	public void testScheduleExpiration() throws InterruptedException {
		manager.scheduleExpiration("myservice", new Time(1,TimeUnit.SECONDS));
		Thread.sleep(1500);
		verify(serviceCacheManager).clearCaches("myservice");
	}

}
