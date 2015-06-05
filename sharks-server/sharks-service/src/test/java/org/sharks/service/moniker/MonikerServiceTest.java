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
import org.sharks.service.moniker.dto.FaoLexFiDocument;
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
	 * Test method for {@link org.sharks.service.moniker.MonikerService#getFigisDocByAcronym(java.lang.String)}.
	 */
	@Test
	public void testGetFigisDocByAcronym() {
		FigisDoc doc = service.getFigisDocByAcronym("ICCAT");
		
		assertNotNull(doc);
	}
	
	@Test
	public void testGetFigisDocByAcronymMissingRFB() {
		FigisDoc doc = service.getFigisDocByAcronym("NOT_EXISTS");
		
		assertNull(doc);
	}
	
	@Test
	public void testGetFigisDocByAcronymMissingDoc() {
		FigisDoc doc = service.getFigisDocByAcronym("APFIC");
		
		assertNull(doc);
	}
	
	@Test
	public void testGetFigisDocByAcronymConnectionError() {
		FigisDoc doc = service.getFigisDocByAcronym("ERROR");
		
		assertNull(doc);
	}
	
	@Test
	public void testGetFigisDocByAcronymConnectionErrorForDoc() {
		FigisDoc doc = service.getFigisDocByAcronym("CACFISH");
		
		assertNull(doc);
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
