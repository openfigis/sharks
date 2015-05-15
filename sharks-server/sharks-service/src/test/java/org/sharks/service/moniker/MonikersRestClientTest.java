/**
 * 
 */
package org.sharks.service.moniker;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestUtils.*;

import java.net.URL;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sharks.service.http.HttpClient;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.moniker.rest.MonikersRestClient.MonikersRestClientException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MonikersRestClientTest {
	
	static MonikersRestClient client;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = Mockito.mock(HttpClient.class);
		
		String content = getResource("/rfb4iso3.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ALB"))).thenReturn(content);
		
		content = getResource("/rfb4iso3_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/NOT_EXISTS"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ERROR"))).thenThrow(new RuntimeException("Get failed"));
		
		
		content = getResource("/figisdoc.xml");
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/7538"))).thenReturn(content);
		
		content = getResource("/figisdoc_not_found.xml");
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/NOT_EXISTS"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/figisdoc/organization/ERROR"))).thenThrow(new RuntimeException("Get failed"));

		
		client = new MonikersRestClient("http://localhost/", httpClient);
	}

	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getRfb4Iso3(java.lang.String)}.
	 */
	@Test
	public void testGetRfbs() {
		List<RfbEntry> entries = client.getRfb4Iso3("ALB");
		
		assertNotNull(entries);
		assertFalse(entries.isEmpty());
	}
	
	@Test
	public void testGetRfbsMissingCountry() {
		List<RfbEntry> entries = client.getRfb4Iso3("NOT_EXISTS");
		
		assertNull(entries);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetRfbsConnectionFail() {
		client.getRfb4Iso3("ERROR");
	}

	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getFigisDoc(java.lang.String)}.
	 */
	@Test
	public void testGetFigisDoc() {
		FigisDoc doc = client.getFigisDoc("7538");
		
		assertNotNull(doc);
		assertNotNull(doc.getFigisId());
	}
	
	@Test
	public void testGetFigisDocWrongOrganization() {
		FigisDoc doc = client.getFigisDoc("NOT_EXISTS");
		
		assertNull(doc);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetFigisDocConnectionError() {
		client.getFigisDoc("ERROR");
	}

}
