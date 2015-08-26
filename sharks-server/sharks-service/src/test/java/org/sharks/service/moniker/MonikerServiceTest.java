/**
 * 
 */
package org.sharks.service.moniker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.createMember;
import static org.sharks.service.test.util.TestModelUtils.createRfb;
import static org.sharks.service.test.util.TestModelUtils.createRfbEntry;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.moniker.rest.MonikersRestClient.MonikersRestClientException;
import org.sharks.service.test.util.NoCache;

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
		
		when(client.getRfb4Iso3("USA")).thenReturn(Arrays.asList(createRfbEntry("7352")));
		when(client.getRfb4Iso3("ITA")).thenReturn(Arrays.asList(createRfbEntry("NOT_EXISTS")));
		when(client.getRfb4Iso3("FRA")).thenReturn(Arrays.asList(createRfbEntry("ERROR")));
		when(client.getRfb4Iso3("NOT_EXISTS")).thenReturn(null);
		when(client.getRfb4Iso3("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
		when(client.getRfbByFid("7352")).thenReturn(createRfb("7352", "ICCAT", createMember("Italy", "ITA")));
		
		when(client.getRfbByAcronym("ICCAT")).thenReturn(createRfb("7352", "ICCAT", createMember("Italy", "ITA")));
		when(client.getRfbByAcronym("APFIC")).thenReturn(createRfb("NOT_EXISTS", "NOT_EXISTS"));
		when(client.getRfbByAcronym("CACFISH")).thenReturn(createRfb("ERROR", "ERROR"));
		when(client.getRfbByAcronym("NOT_EXISTS")).thenReturn(null);
		when(client.getRfbByAcronym("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
		FaoLexFiDocument lexDoc = new FaoLexFiDocument();
		lexDoc.setFaolexId("1");
		when(client.getFaoLexDocuments("USA")).thenReturn(Arrays.asList(lexDoc));
		when(client.getFaoLexDocuments("NOT_EXISTS")).thenReturn(null);
		when(client.getFaoLexDocuments("ERROR")).thenThrow(new MonikersRestClientException("",null));
		
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
	 * Test method for {@link org.sharks.service.moniker.MonikerService#getRfb(String)}.
	 */
	@Test
	public void testGetRfb() {
		Rfb entry = service.getRfb("ICCAT");
		
		assertNotNull(entry);
	}
	
	@Test
	public void testGetRfbMissingRFB() {
		Rfb entry = service.getRfb("NOT_EXISTS");
		
		assertNull(entry);
	}
	
	@Test
	public void testGetRfbError() {
		Rfb entry = service.getRfb("ERROR");
		
		assertNull(entry);
	}
	
	/**
	 * Test method for {@link org.sharks.service.moniker.MonikerService#getFaoLexDocumentsForCountry(java.lang.String)}.
	 */
	@Test
	public void testGetFaoLexDocumentsForCountry() {
		List<FaoLexFiDocument> docs = service.getFaoLexDocumentsForCountry("USA");
		
		assertNotNull(docs);
	}
	
	@Test
	public void testGetFaoLexDocumentsForCountryMissingCountry() {
		List<FaoLexFiDocument> docs = service.getFaoLexDocumentsForCountry("NOT_EXISTS");
		
		assertNotNull(docs);
		assertTrue(docs.isEmpty());
	}
	
	@Test
	public void testGetFaoLexDocumentsForCountryConnectionError() {
		List<FaoLexFiDocument> docs = service.getFaoLexDocumentsForCountry("ERROR");
		
		assertNotNull(docs);
		assertTrue(docs.isEmpty());
	}

}
