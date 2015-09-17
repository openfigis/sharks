package org.sharks.service.cites;

import static org.junit.Assert.assertTrue;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.config.ConfigurationProducer;
import org.sharks.service.cache.InMemoryCache;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.http.DefaultHttpClient;

@RunWith(CdiRunner.class)
@AdditionalClasses({ CitesServiceImpl.class, DefaultHttpClient.class, ConfigurationProducer.class })
public class CitesServiceImplTest {

	String iso3 = "DEU";

	@Test
	public void isMember() {
		assertTrue(citesService.isMember(iso3));
	}

	@Test
	public void getParties() {
		assertTrue(citesService.getParties().size() > 175);
	}

	@Inject
	CitesService citesService;

	@Produces
	private <K, V> ServiceCache<K, V> setupCache() {
		return new InMemoryCache<K, V>();
	}

	@BeforeClass
	public static void before() {
		System.setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME,
				"../../sharks-deploy/conf/dev/sharks.properties");
	}

}