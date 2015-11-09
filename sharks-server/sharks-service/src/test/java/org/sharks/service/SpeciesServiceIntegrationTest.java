package org.sharks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.config.ConfigurationProducer;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.InMemoryCache;
import org.sharks.service.cache.ServiceCacheManager;
import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;
import org.sharks.service.cache.ServiceCacheProducer;
import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.geoserver.GeoServerServiceImpl;
import org.sharks.service.geoserver.rest.GeoServerRestClient;
import org.sharks.service.impl.CachesServiceImpl;
import org.sharks.service.impl.SpeciesServiceImpl;
import org.sharks.service.refpub.RefPubServiceImpl;
import org.sharks.storage.dao.SpeciesDaoImpl;

/**
 * @author Erik van Ingen
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ ServiceCacheProducer.class, SpeciesServiceImpl.class, SpeciesDaoImpl.class,
		RefPubServiceImpl.class, GeoServerServiceImpl.class, GeoServerRestClient.class, Producers.class,
		org.sharks.storage.dao.Producers.class, ConfigurationProducer.class,
		org.sharks.service.http.DefaultHttpClient.class, CachesServiceImpl.class })
public class SpeciesServiceIntegrationTest {

	@Inject
	SpeciesService service;

	@BeforeClass
	public static void before() {
		System.setProperty(ConfigurationProducer.CONFIG_LOCATION_PROPERTY_NAME,
				"..\\..\\sharks-deploy\\conf\\dev\\sharks.properties");
	}

	@Test
	@Ignore
	public void testGetSpeciesBSK() {
		String species = "BSK";

		SpeciesDetails details = service.getSpecies(species);
		assertNotNull(details);
		assertEquals(species, details.getAlphaCode());

	}

	@Produces
	@Singleton
	private ServiceCacheManager setupServiceCacheManager() {
		ServiceCacheManager manager = mock(ServiceCacheManager.class);
		when(manager.getOrCreateCache(new ServiceInfo("refpub", ServiceType.EXTERNAL), "countryIso3"))
				.thenReturn(new InMemoryCache<Object, Object>());
		when(manager.getOrCreateCache(new ServiceInfo("refpub", ServiceType.EXTERNAL), "countryIso2"))
				.thenReturn(new InMemoryCache<Object, Object>());
		when(manager.getOrCreateCache(new ServiceInfo("refpub", ServiceType.EXTERNAL), "species"))
				.thenReturn(new InMemoryCache<Object, Object>());
		when(manager.getOrCreateCache(new ServiceInfo("geoserver", ServiceType.EXTERNAL), "specieslist"))
				.thenReturn(new InMemoryCache<Object, Object>());

		return manager;
	}

}
