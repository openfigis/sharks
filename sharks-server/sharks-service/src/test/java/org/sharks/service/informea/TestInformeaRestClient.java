package org.sharks.service.informea;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.service.http.DefaultHttpClient;
import org.sharks.service.http.HttpClient;
import org.sharks.service.informea.dto.InformeaParties;
import org.sharks.service.informea.rest.InformeaRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestInformeaRestClient {
	
	private static InformeaRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		client = new InformeaRestClient(httpClient, "https://cites.org/ws/parties");
	}

	@Test @Ignore
	public void testGetCmsParties() {
		InformeaParties parties = client.getParties();
		assertNotNull(parties);
		System.out.println(parties);
	}

}
