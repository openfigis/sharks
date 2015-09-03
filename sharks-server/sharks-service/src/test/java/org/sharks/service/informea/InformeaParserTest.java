package org.sharks.service.informea;


import static org.junit.Assert.*;
import static org.sharks.service.test.util.TestUtils.getResource;

import java.util.Set;
import java.util.stream.Collectors;

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
	
	@Test
	public void diff() {
		String content = getResource("/informea/cites_parties.xml");
		InformeaParties citesParties = parser.parseParties(content);
		
		content = getResource("/informea/cms_parties.xml");
		InformeaParties cmsParties = parser.parseParties(content);
		
		System.out.println("cites: "+citesParties.getCountries().size());
		System.out.println("cms: "+cmsParties.getCountries().size());
		
		Set<String> cites = citesParties.getCountries().stream().map((c)->c.getIso3()).collect(Collectors.toSet());
		Set<String> cms = cmsParties.getCountries().stream().map((c)->c.getIso3()).collect(Collectors.toSet());
		
		cites.removeAll(cms);
		System.out.println(cites);
		
	}
}
