package org.sharks.service.cites;


import static org.junit.Assert.*;
import static org.sharks.service.test.util.TestUtils.getResource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.cites.dto.CitesCountry;
import org.sharks.service.cites.dto.CitesParties;
import org.sharks.service.cites.rest.CitesParser;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CitesParserTest {
	
	private static CitesParser parser;
	
	@BeforeClass
	public static void setupContext() {
		parser = new CitesParser();
	}

	@Test
	public void testRfb4Iso3ResponseParsing() {
		String content = getResource("/cites/cites_parties.xml");
		CitesParties parties = parser.parseParties(content);
		
		assertNotNull(parties);
		assertFalse(parties.getCountries().isEmpty());
		
		CitesCountry country = parties.getCountries().get(0);
		
		assertNotNull(country.getIso2());
		assertNotNull(country.getIso3());
		assertNotNull(country.getName());
	}
}
