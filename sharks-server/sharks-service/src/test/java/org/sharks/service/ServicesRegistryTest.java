/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.CachedInterceptorTest.MyService;
import org.sharks.service.cache.ServiceCacheProducer;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({})
public class ServicesRegistryTest {
	
	@Inject
	private ServicesRegistry registry;
	
	@Inject
	private MyService myservice;

	/**
	 * Test method for {@link org.sharks.service.ServicesRegistry#getServices(org.sharks.service.Service.ServiceType[])}.
	 */
	@Test
	public void testGetServices() {
		List<String> services = registry.getServices();
		System.out.println(services);
	}
	
	@Service(name="myservice",type=ServiceType.INTERNAL)
	public static class MyService {
		
	}

}
