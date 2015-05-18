package org.sharks.service.moniker;

import static org.junit.Assert.*;
import static org.sharks.service.util.TestUtils.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.FigisDoc.Member;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.RfbEntry;
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
		String content = getResource("/rfb4iso3.xml");
		MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		RfbEntry first = response.getOutput().getItems().get(0);

		assertNotNull(first.getFigisId());
		
	}
	
	@Test
	public void testRfb4Iso3NotFoundResponseParsing() {
		String content = getResource("/rfb4iso3_not_found.xml");
		MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		
		assertNull(response.getOutput().getItems());
				
	}
	
	@Test
	public void testRfbResponseParsing() {
		String content = getResource("/rfb.xml");
		MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		RfbEntry first = response.getOutput().getItems().get(0);

		assertNotNull(first.getFid());
		
	}
	
	@Test
	public void testRfbNotFoundResponseParsing() {
		String content = getResource("/rfb_not_found.xml");
		MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		
		assertNull(response.getOutput().getItems());
				
	}
	
	@Test
	public void testFigisDocParsing() {
		String content = getResource("/figisdoc.xml");
		FigisDoc doc = parser.parseFigisDoc(content);
		
		assertNotNull(doc);
		
		assertNotNull(doc.getFigisId());
		assertNotNull(doc.getAcronym());
		assertNotNull(doc.getWebsite());
		assertNotNull(doc.getImageId());
		
		assertNotNull(doc.getMembers());
		assertFalse(doc.getMembers().isEmpty());
		
		Member member = doc.getMembers().get(0);
		assertNotNull(member.getIso3code());
		assertNotNull(member.getEnglishName());
	}

	
}
