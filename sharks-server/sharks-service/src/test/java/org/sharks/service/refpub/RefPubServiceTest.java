package org.sharks.service.refpub;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.*;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient.RefPubRestClientException;
import org.sharks.service.util.NoCache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({RefPubServiceImpl.class, NoCache.class})
public class RefPubServiceTest {
	
	@Inject
	RefPubService service;
	
	@Produces
	private RefPubRestClient setupRefPubRestClient() {
		RefPubRestClient client = Mockito.mock(RefPubRestClient.class);
		
		when(client.getCountryByIso3("AFG")).thenReturn(aRefPubCountry());
		when(client.getCountryByIso3("NOT_EXISTS")).thenReturn(null);
		when(client.getCountryByIso3("ERROR")).thenThrow(new RefPubRestClientException("Get error",null));
		
		when(client.getCountryByIso2("AF")).thenReturn(aRefPubCountry());
		when(client.getCountryByIso2("NOT_EXISTS")).thenReturn(null);
		when(client.getCountryByIso2("ERROR")).thenThrow(new RefPubRestClientException("Get error",null));
		
		when(client.getSpecies("ALV")).thenReturn(aRefPubSpecies());
		when(client.getSpecies("NOT_EXISTS")).thenReturn(null);
		when(client.getSpecies("ERROR")).thenThrow(new RefPubRestClientException("Get error",null));
		
		return client;
	}
	

	@Test
	public void testGetCountryByIso3() {
		RefPubCountry country = service.getCountryByIso3("AFG");
		assertNotNull(country);
	}
	
	@Test
	public void testGetCountryByIso3MissingCountry() {
		RefPubCountry country = service.getCountryByIso3("NOT_EXISTS");
		assertNull(country);
	}
	
	@Test
	public void testGetCountryByIso3ConnectionError() {
		RefPubCountry country = service.getCountryByIso3("ERROR");
		assertNull(country);
	}
	
	
	@Test
	public void testGetCountryByIso2() {
		RefPubCountry country = service.getCountryByIso2("AF");
		assertNotNull(country);
	}
	
	@Test
	public void testGetCountryByIso2MissingCountry() {
		RefPubCountry country = service.getCountryByIso2("NOT_EXISTS");
		assertNull(country);
	}
	
	@Test
	public void testGetCountryByIso2ConnectionError() {
		RefPubCountry country = service.getCountryByIso2("ERROR");
		assertNull(country);
	}

	@Test
	public void testGetSpecies() {
		RefPubSpecies species = service.getSpecies("ALV");
		assertNotNull(species);
	}
	
	@Test
	public void testGetSpeciesMissingCountry() {
		RefPubSpecies species = service.getSpecies("NOT_EXISTS");
		assertNull(species);
	}
	
	@Test
	public void testGetSpeciesConnectionError() {
		RefPubSpecies species = service.getSpecies("ERROR");
		assertNull(species);
	}

}
