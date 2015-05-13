package org.sharks.service.moniker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import javax.xml.bind.JAXBException;

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
	public void testRfbResponseParsing() throws JAXBException {
		InputStream file = MonikersParserTest.class.getResourceAsStream("/moniker_rfb4iso3.xml");
		MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(file);
		
		assertNotNull(response);
		assertNotNull(response.getOutput());
		assertFalse(response.getOutput().getItems().isEmpty());
		
		RfbEntry first = response.getOutput().getItems().get(0);

		assertNotNull(first.getFigisId());
		assertNotNull(first.getUri());
		assertNotNull(first.getLang());
		assertNotNull(first.getMetaId());
		assertNotNull(first.getName());
		
	}
	
	@Test
	public void testFigisDocParsing() throws JAXBException {
		InputStream file = MonikersParserTest.class.getResourceAsStream("/figisdoc.xml");
		FigisDoc doc = parser.parseFigisDoc(file);
		
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
