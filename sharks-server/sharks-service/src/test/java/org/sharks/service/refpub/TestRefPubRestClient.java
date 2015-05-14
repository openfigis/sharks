package org.sharks.service.refpub;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.service.http.DefaultHttpClient;
import org.sharks.service.http.HttpClient;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestRefPubRestClient {
	
	private static RefPubRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		client = new RefPubRestClient("http://figisapps.fao.org/refpub-web/rest/", httpClient);
	}

	@Test @Ignore
	public void testGetCountry() {
		RefPubCountry country = client.getCountry("ALB");
		assertNotNull(country);
		System.out.println(country);
		System.out.println(country.getFisheryCommissions());
	}
	
	@Test @Ignore
	public void testGetSpecies() {
		RefPubSpecies species = client.getSpecies("ALV");
		assertNotNull(species);
		System.out.println(species);
		System.out.println(species.getFicItem());
	}

}
