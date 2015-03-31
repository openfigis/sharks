package org.sharks.service.refpub.rest;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.service.refpub.dto.RefPubCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestRefPubRestClient {
	
	private static RefPubRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = new RefPubRestClient("http://figisapps.fao.org/refpub-web/rest/");
	}

	@Test @Ignore
	public void testGetCountry() {
		RefPubCountry country = client.getCountry("AFG");
		assertNotNull(country);
		System.out.println(country);
	}
	
	@Test @Ignore
	public void testListAllCountries() {
		List<RefPubCountry> countries = client.listAllCountries();
		assertNotNull(countries);
		System.out.println(countries.size());
	}

}
