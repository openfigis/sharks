package org.sharks.service.refpub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.sharks.service.util.TestUtils.getResource;

import javax.xml.bind.JAXBException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubParser;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubParserTest {
	
	private static RefPubParser parser;

	@BeforeClass
	public static void initParser() {
		parser = new RefPubParser();
	}
	
	@Test
	public void testRefPubCountryParsing() throws JAXBException {
		String content = getResource("/country.xml");
		RefPubCountry country = parser.parseCountry(content);
		
		assertNotNull(country);
		assertNotNull(country.getMultilingualOfficialName());
		assertNotNull(country.getUnIso3Code());
		
		assertNotNull(country.getFisheryCommissions());
		assertFalse(country.getFisheryCommissions().isEmpty());
		
		assertNotNull(country.getContinent());
	}
	
	@Test
	public void testRefPubCountryNotFoundParsing() throws JAXBException {
		String content = getResource("/country_not_found.xml");
		RefPubCountry country = parser.parseCountry(content);
		
		assertNull(country);
	}
	
	@Test
	public void testRefPubSpeciesParsing() throws JAXBException {
		String content = getResource("/species.xml");
		RefPubSpecies species = parser.parseSpecies(content);
		
		assertNotNull(species);
		assertNotNull(species.getLongNames());
		assertNotNull(species.getFicItem());
	}
	
	@Test
	public void testRefPubSpeciesNotFoundParsing() throws JAXBException {
		String content = getResource("/species_not_found.xml");
		RefPubSpecies species = parser.parseSpecies(content);
		
		assertNull(species);
	}

}
