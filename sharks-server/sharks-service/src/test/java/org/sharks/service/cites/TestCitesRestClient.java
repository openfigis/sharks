package org.sharks.service.cites;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.sharks.service.cites.dto.CitesParties;
import org.sharks.service.cites.rest.CitesRestClient;
import org.sharks.service.http.DefaultHttpClient;
import org.sharks.service.http.HttpClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestCitesRestClient {
	
	private static CitesRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		client = new CitesRestClient(httpClient, "https://cites.org/ws/parties");
	}

	@Test
	public void testGetParties() {
		CitesParties parties = client.getParties();
		assertNotNull(parties);
		System.out.println(parties);
	}

}
