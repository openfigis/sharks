/**
 * 
 */
package org.sharks.service.geoserver;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.sharks.service.util.TestUtils.getResource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sharks.service.geoserver.dto.SpeciesList;
import org.sharks.service.geoserver.rest.GeoServerRestClient;
import org.sharks.service.geoserver.rest.GeoServerRestClient.GeoServerRestClientException;
import org.sharks.service.http.HttpClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class GeoServerRestClientTest {
	
	private static HttpClient httpClient;
	private static GeoServerRestClient client;
	
	@BeforeClass
	public static void setupClient() throws MalformedURLException {
		httpClient = Mockito.mock(HttpClient.class);
		client = new GeoServerRestClient(httpClient, "http://figisapps.fao.org/figis/geoserver/factsheets/js/specieslist.xml");
	}

	/**
	 * Test method for {@link org.sharks.service.geoserver.rest.GeoServerRestClient#getSpeciesList()}.
	 * @throws MalformedURLException 
	 */
	@Test
	public void testGetSpeciesList() throws MalformedURLException {
		String content = getResource("/geoserver/specieslist.xml");
		reset(httpClient);
		when(httpClient.get(new URL("http://figisapps.fao.org/figis/geoserver/factsheets/js/specieslist.xml"))).thenReturn(content);

		SpeciesList list = client.getSpeciesList();
		assertNotNull(list);
		assertFalse(list.getItems().isEmpty());
	}
	
	@Test(expected=GeoServerRestClientException.class)
	public void testGetSpeciesListConnectionError() throws MalformedURLException {
		reset(httpClient);
		when(httpClient.get(new URL("http://figisapps.fao.org/figis/geoserver/factsheets/js/specieslist.xml"))).thenThrow(new RuntimeException("connection fail"));
		
		client.getSpeciesList();
	}

}
