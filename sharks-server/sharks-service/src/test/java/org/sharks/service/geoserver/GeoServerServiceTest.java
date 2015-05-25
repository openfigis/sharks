/**
 * 
 */
package org.sharks.service.geoserver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.createSpeciesList;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.cache.InMemoryCache;
import org.sharks.service.cache.ServiceCache;
import org.sharks.service.geoserver.rest.GeoServerRestClient;
import org.sharks.service.geoserver.rest.GeoServerRestClient.GeoServerRestClientException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({GeoServerServiceImpl.class})
public class GeoServerServiceTest {
	
	@Inject
	private GeoServerService service;
	
	@Inject
	private GeoServerRestClient client;
	
	@Produces @Singleton
	private GeoServerRestClient setupGeoServerRestClient() {
		return Mockito.mock(GeoServerRestClient.class);
	}
	
	@Produces
	private <K,V> ServiceCache<K, V> setupCache() {
		return new InMemoryCache<K, V>();
	}

	/**
	 * Test method for {@link org.sharks.service.geoserver.GeoServerServiceImpl#hasSpeciesDistributionMap(java.lang.String)}.
	 */
	@Test
	public void testHasSpeciesDistributionMap() {
		reset(client);
		when(client.getSpeciesList()).thenReturn(createSpeciesList("ALV"));
		
		boolean hasMap = service.hasSpeciesDistributionMap("ALV");
		assertTrue(hasMap);
	}
	
	@Test
	public void testHasSpeciesDistributionMapNoMap() {
		reset(client);
		when(client.getSpeciesList()).thenReturn(createSpeciesList("ALV"));
		
		boolean hasMap = service.hasSpeciesDistributionMap("NOT_EXISTS");
		assertFalse(hasMap);
	}
	
	@Test
	public void testHasSpeciesDistributionMapClientError() {
		reset(client);
		when(client.getSpeciesList()).thenThrow(new GeoServerRestClientException("error", null));
		
		boolean hasMap = service.hasSpeciesDistributionMap("ALV");
		assertFalse(hasMap);
	}

}
