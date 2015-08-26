package org.sharks.service.moniker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.sharks.service.test.util.TestUtils.getResource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.moniker.dto.ErrorElement;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.moniker.dto.Rfb.Member;
import org.sharks.service.moniker.rest.MonikersParser;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MonikersParserTest {
	
	private static MonikersParser parser;
	
	@BeforeClass
	public static void setupContext() {
		parser = new MonikersParser();
	}

	@Test
	public void testRfb4Iso3ResponseParsing() {
		String content = getResource("/monikers/rfb4iso3.xml");
		MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		Rfb first = response.getOutput().getItems().get(0);

		assertNotNull(first.getFigisId());
	}
	
	@Test
	public void testRfb4Iso3NotFoundResponseParsing() {
		String content = getResource("/monikers/rfb4iso3_not_found.xml");
		MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		
		assertNull(response.getOutput().getItems());	
	}
	
	@Test
	public void testRfbResponseParsing() {
		String content = getResource("/monikers/rfb.xml");
		MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		Rfb first = response.getOutput().getItems().get(0);

		assertNotNull(first.getFid());
		assertNotNull(first.getAcronym());
		assertNotNull(first.getLogo());
		assertNotNull(first.getWebsite());
		assertNotNull(first.getLink());
		
		assertNotNull(first.getMembers());
		assertFalse(first.getMembers().isEmpty());
		
		Member member = first.getMembers().get(0);
		assertNotNull(member.getIso3());
		assertNotNull(member.getEnglishName());
	}
	
	@Test
	public void testRfbNotFoundResponseParsing() {
		String content = getResource("/monikers/rfb_not_found.xml");
		MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		
		assertNull(response.getOutput().getItems());
				
	}
	
	@Test
	public void testFaoLexFIResponseParsing() {
		String content = getResource("/monikers/faolexfi.xml");
		MonikerResponse<FaoLexFiDocument> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		FaoLexFiDocument first = response.getOutput().getItems().get(0);
		
		assertNotNull(first.getUri());
		assertNotNull(first.getTitle());
		assertNotNull(first.getDateOfText());
		assertNull(first.getDateOfConsolidation());
		assertNotNull(first.getFaolexId());
		
		FaoLexFiDocument second = response.getOutput().getItems().get(2);

		//test date parsing
		assertNull(second.getDateOfText());
		assertNotNull(second.getDateOfOriginalText());
		assertNotNull(second.getDateOfConsolidation());
	}
	
	@Test
	public void testFaoLexFINotFoundResponseParsing() {
		String content = getResource("/monikers/faolexfi_not_found.xml");
		MonikerResponse<FaoLexFiDocument> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		
		assertEquals(1, response.getOutput().getItems().size());
		Object errorElement = response.getOutput().getItems().get(0);
		assertTrue(errorElement instanceof ErrorElement);
	}

	
}
