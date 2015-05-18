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
import org.sharks.service.util.NoCache;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({MonikerServiceImpl.class, NoCache.class})
public class MonikerServiceTest {
	
	@Inject
	MonikerService service;
	
	@Produces
	protected MonikersRestClient getRestClient() {
		MonikersRestClient client = Mockito.mock(MonikersRestClient.class);
		
		when(client.getRfb4Iso3("USA")).thenReturn(Arrays.asList(new RfbEntry("7352", null)));
		when(client.getRfb4Iso3("ITA")).thenReturn(Arrays.asList(new RfbEntry("NOT_EXISTS", null)));
		when(client.getRfb4Iso3("FRA")).thenReturn(Arrays.asList(new RfbEntry("ERROR", null)));
		when(client.getRfb4Iso3("NOT_EXISTS")).thenReturn(null);
		when(client.getRfb4Iso3("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
		when(client.getRfb("ICCAT")).thenReturn(new RfbEntry(null, "7352"));
		when(client.getRfb("APFIC")).thenReturn(new RfbEntry(null, "NOT_EXISTS"));
		when(client.getRfb("CACFISH")).thenReturn(new RfbEntry(null, "ERROR"));
		when(client.getRfb("NOT_EXISTS")).thenReturn(null);
		when(client.getRfb("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
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
	
	/**
	 * Test method for {@link org.sharks.service.moniker.MonikerService#getFigisDocByAcronym(java.lang.String)}.
	 */
	@Test
	public void testGetFigisDocByAcronym() {
		FigisDoc rfb = service.getFigisDocByAcronym("ICCAT");
		
		assertNotNull(rfb);
	}
	
	@Test
	public void testGetFigisDocByAcronymMissingRFB() {
		FigisDoc rfb = service.getFigisDocByAcronym("NOT_EXISTS");
		
		assertNull(rfb);
	}
	
	@Test
	public void testGetFigisDocByAcronymMissingDoc() {
		FigisDoc rfb = service.getFigisDocByAcronym("APFIC");
		
		assertNull(rfb);
	}
	
	@Test
	public void testGetFigisDocByAcronymConnectionError() {
		FigisDoc rfb = service.getFigisDocByAcronym("ERROR");
		
		assertNull(rfb);
	}
	
	@Test
	public void testGetFigisDocByAcronymConnectionErrorForDoc() {
		FigisDoc rfb = service.getFigisDocByAcronym("CACFISH");
		
		assertNull(rfb);
	}

}
