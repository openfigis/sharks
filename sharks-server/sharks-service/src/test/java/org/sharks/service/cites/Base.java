package org.sharks.service.cites;

import javax.enterprise.inject.Produces;

import org.jglue.cdiunit.AdditionalClasses;
import org.junit.BeforeClass;
import org.sharks.config.ConfigurationProducer;
import org.sharks.service.cache.InMemoryCache;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.http.DefaultHttpClient;

@AdditionalClasses({ DefaultHttpClient.class, ConfigurationProducer.class })
public class Base {

	@Produces
	public <K, V> ServiceCache<K, V> setupCache() {
		return new InMemoryCache<K, V>();
	}

	@BeforeClass
	public static void before() {
		System.setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME,
				"../../sharks-deploy/conf/dev/sharks.properties");
	}
}