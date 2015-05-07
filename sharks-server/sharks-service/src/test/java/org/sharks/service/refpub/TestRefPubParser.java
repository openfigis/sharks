package org.sharks.service.refpub;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

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
public class TestRefPubParser {
	
	private static RefPubParser parser;
	
	@BeforeClass
	public static void setupParser() throws JAXBException {
		parser = new RefPubParser();
	}

	@Test
	public void testRefPubCountryParsing() throws JAXBException {
		InputStream file = TestRefPubParser.class.getResourceAsStream("/country.xml");
		RefPubCountry country = parser.parseCountry(file);
		
		assertNotNull(country);
		assertNotNull(country.getMultilingualOfficialName());
		assertNotNull(country.getUnIso3Code());
		
		assertNotNull(country.getFisheryCommissions());
		assertFalse(country.getFisheryCommissions().isEmpty());
	}
	
	@Test
	public void testRefPubSpeciesParsing() throws JAXBException {
		InputStream file = TestRefPubParser.class.getResourceAsStream("/species.xml");
		RefPubSpecies species = parser.parseSpecies(file);
		
		assertNotNull(species);
		assertNotNull(species.getLongNames());
		assertNotNull(species.getFicItem());
	}

}
