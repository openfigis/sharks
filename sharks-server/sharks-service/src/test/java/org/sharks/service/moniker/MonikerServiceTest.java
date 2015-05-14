/**
 * 
 */
package org.sharks.service.moniker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.moniker.rest.MonikersRestClient.MonikersRestClientException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({MonikerServiceImpl.class})
public class MonikerServiceTest {
	
	@Inject
	MonikerService service;
	
	@Produces
	protected MonikersRestClient getRestClient() {
		MonikersRestClient client = Mockito.mock(MonikersRestClient.class);
		
		when(client.getRfbs("USA")).thenReturn(Arrays.asList(new RfbEntry("7352")));
		when(client.getRfbs("ITA")).thenReturn(Arrays.asList(new RfbEntry("NOT_EXISTS")));
		when(client.getRfbs("FRA")).thenReturn(Arrays.asList(new RfbEntry("ERROR")));
		when(client.getRfbs("NOT_EXISTS")).thenReturn(null);
		when(client.getRfbs("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
		FigisDoc doc = new FigisDoc();
		doc.setAcronym("ICCAT");
		when(client.getFigisDoc("7352")).thenReturn(doc);
		when(client.getFigisDoc("NOT_EXISTS")).thenReturn(null);
		when(client.getFigisDoc("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
		return client;
	}

	/**
	 * Test method for {@link org.sharks.service.moniker.MonikerService#getRfbsForCountry(java.lang.String)}.
	 */
	@Test
	public void testGetRfbsForCountry() {
		List<String> rfbs = service.getRfbsForCountry("USA");
		
		assertNotNull(rfbs);
		assertFalse(rfbs.isEmpty());
		assertTrue(rfbs.contains("ICCAT"));
	}
	
	@Test
	public void testGetRfbsForCountryMissingCountry() {
		List<String> rfbs = service.getRfbsForCountry("NOT_EXISTS");
		
		assertNotNull(rfbs);
		assertTrue(rfbs.isEmpty());
	}
	
	@Test
	public void testGetRfbsForCountryMissingOrganization() {
		List<String> rfbs = service.getRfbsForCountry("ITA");
		
		assertNotNull(rfbs);
		assertTrue(rfbs.isEmpty());
	}
	
	@Test
	public void testGetRfbsForCountryConnectionError() {
		List<String> rfbs = service.getRfbsForCountry("ERROR");
		
		assertNotNull(rfbs);
		assertTrue(rfbs.isEmpty());
	}
	
	@Test
	public void testGetRfbsForCountryConnectionErrorForOrganization() {
		List<String> rfbs = service.getRfbsForCountry("FRA");
		
		assertNotNull(rfbs);
		assertTrue(rfbs.isEmpty());
	}

}
