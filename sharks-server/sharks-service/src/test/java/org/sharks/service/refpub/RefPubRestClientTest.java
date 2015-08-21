/**
 * 
 */
package org.sharks.service.refpub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestUtils.getResource;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sharks.service.http.HttpClient;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient.RefPubRestClientException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubRestClientTest {
	
	static RefPubRestClient client;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = Mockito.mock(HttpClient.class);
		
		String content = getResource("/refpub/country.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/ALB/xml"))).thenReturn(content);
		
		content = getResource("/refpub/country_not_found.xml");
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/NOT_EXISTS/xml"))).thenReturn(content);
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/N%2FA/xml"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/concept/Country/codesystem/UN-ISO3/code/ERROR/xml")))
		.thenThrow(new RuntimeException("Get failed"));
		
		
		content = getResource("/refpub/species.xml");
		when(httpClient.get(new URL("http://localhost/concept/Species/codesystem/ASFIS/code/ALV/xml"))).thenReturn(content);
		
		content = getResource("/refpub/species_not_found.xml");
		when(httpClient.get(new URL("http://localhost/concept/Species/codesystem/ASFIS/code/NOT_EXISTS/xml"))).thenReturn(content);
		when(httpClient.get(new URL("http://localhost/concept/Species/codesystem/ASFIS/code/N%2FA/xml"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/concept/Species/codesystem/ASFIS/code/ERROR/xml"))).thenThrow(new RuntimeException("Get failed"));
		
		client = new RefPubRestClient("http://localhost/", httpClient);
	}

	/**
	 * Test method for {@link org.sharks.service.refpub.rest.RefPubRestClient#getCountryByIso3(java.lang.String)}.
	 */
	@Test
	public void testGetCountryByIso3() {
		RefPubCountry country = client.getCountryByIso3("ALB");
		
		assertNotNull(country);
		assertEquals("ALB", country.getUnIso3Code());
	}
	
	@Test
	public void testGetCountryByIso3Missing() {
		RefPubCountry country = client.getCountryByIso3("NOT_EXISTS");
		
		assertNull(country);
	}
	
	@Test(expected=RefPubRestClientException.class)
	public void testGetCountryByIso3ConnectionError() {
		client.getCountryByIso3("ERROR");
	}
	
	@Test
	public void testGetCountryByIso3WithDisallowedCharInCode() {
		RefPubCountry country = client.getCountryByIso3("N/A");
		
		assertNull(country);
	}

	/**
	 * Test method for {@link org.sharks.service.refpub.rest.RefPubRestClient#getSpecies(java.lang.String)}.
	 */
	@Test
	public void testGetSpecies() {
		RefPubSpecies species = client.getSpecies("ALV");
		
		assertNotNull(species);
		assertNotNull(species.getFicItem());
	}
	
	@Test
	public void testGetSpeciesMissing() {
		RefPubSpecies species = client.getSpecies("NOT_EXISTS");
		
		assertNull(species);
	}
	
	@Test(expected=RefPubRestClientException.class)
	public void testGetSpeciesConnectionError() {
		client.getSpecies("ERROR");
	}
	
	@Test
	public void testGetSpeciesWithDisallowedCharsInCode() {
		RefPubSpecies species = client.getSpecies("N/A");
		
		assertNull(species);
	}
	

}
