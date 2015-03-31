package org.sharks.service.refpub.dto;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestRefPubParsing {
	
	private static Unmarshaller unmarshaller;
	
	@BeforeClass
	public static void setupContext() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(RefPubCountry.class, RefPubCountries.class);
		unmarshaller = context.createUnmarshaller();
	}

	@Test
	public void testRefPubCountryParsing() throws JAXBException {
		InputStream file = TestRefPubParsing.class.getResourceAsStream("/country.xml");
		RefPubCountry country = (RefPubCountry) unmarshaller.unmarshal(file);
		
		assertNotNull(country);
		assertNotNull(country.getMultilingualName());
		assertNotNull(country.getMultilingualName().getArabic());
		assertNotNull(country.getCodeList());
		assertNotNull(country.getUnIso3Code());
	}
	
	@Test
	public void testRePubCountriesParsing() throws JAXBException {
		InputStream file = TestRefPubParsing.class.getResourceAsStream("/countries.xml");
		RefPubCountries countries = (RefPubCountries) unmarshaller.unmarshal(file);
		
		assertNotNull(countries);
		assertNotNull(countries.getCountries());
		assertNotNull(countries.getLinks());
		assertNotNull(countries.getNextLink());
	}

}
