package org.sharks.service.informea;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.sharks.service.test.util.TestUtils.getResource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.informea.dto.InformeaParties;
import org.sharks.service.informea.rest.InformeaParser;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class InformeaParserTest {
	
	private static InformeaParser parser;
	
	@BeforeClass
	public static void setupContext() {
		parser = new InformeaParser();
	}

	@Test
	public void testParsePartiesCites() {
		String content = getResource("/informea/cites_parties.xml");
		InformeaParties parties = parser.parseParties(content);
		
		assertNotNull(parties);
		assertFalse(parties.getCountries().isEmpty());
		
		InformeaCountry country = parties.getCountries().get(0);
		
		assertNotNull(country.getIso2());
		assertNotNull(country.getIso3());
		assertNotNull(country.getName());
	}
	
	@Test
	public void testParsePartiesCms() {
		String content = getResource("/informea/cms_parties.xml");
		InformeaParties parties = parser.parseParties(content);
		
		assertNotNull(parties);
		assertFalse(parties.getCountries().isEmpty());
		
		InformeaCountry country = parties.getCountries().get(0);
		
		assertNotNull(country.getIso2());
		assertNotNull(country.getIso3());
		assertNotNull(country.getName());
	}

}
