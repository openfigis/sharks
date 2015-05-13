package org.sharks.service.moniker;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.RfbEntry;

import static org.junit.Assert.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestMonikerDtoParsing {
	
	private static Unmarshaller unmarshaller;
	
	@BeforeClass
	public static void setupContext() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(RfbEntry.class, MonikerResponse.class);
		unmarshaller = context.createUnmarshaller();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testRfbResponseParsing() throws JAXBException {
		InputStream file = TestMonikerDtoParsing.class.getResourceAsStream("/moniker_rfb4iso3.xml");
		MonikerResponse<RfbEntry> response = (MonikerResponse<RfbEntry>) unmarshaller.unmarshal(file);
		
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
	
}
