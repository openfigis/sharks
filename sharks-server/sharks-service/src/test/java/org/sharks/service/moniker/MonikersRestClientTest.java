/**
 * 
 */
package org.sharks.service.moniker;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestUtils.getResource;

import java.net.URL;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.sharks.service.http.HttpClient;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.Rfb;
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
		
		String content = getResource("/monikers/rfb4iso3.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ALB"))).thenReturn(content);
		
		content = getResource("/monikers/rfb4iso3_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb4iso3/NOT_EXISTS"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/rfb4iso3/ERROR"))).thenThrow(new RuntimeException("Get failed"));
		
		content = getResource("/monikers/rfb.xml");
		when(httpClient.get(new URL("http://localhost/rfb/ICCAT"))).thenReturn(content);
		when(httpClient.get(new URL("http://localhost/rfb/9294"))).thenReturn(content);
		
		content = getResource("/monikers/rfb_not_found.xml");
		when(httpClient.get(new URL("http://localhost/rfb/NOT_EXISTS"))).thenReturn(content);
		
		when(httpClient.get(new URL("http://localhost/rfb/ERROR"))).thenThrow(new RuntimeException("Get failed"));
		
		
		content = getResource("/monikers/faolexfi.xml");
		when(httpClient.get(new URL("http://localhost/faolexfi/kwid=089/iso3=aus"))).thenReturn(content);
		content = getResource("/monikers/faolexfi_not_found.xml");
		when(httpClient.get(new URL("http://localhost/faolexfi/kwid=089/iso3=NOT_EXISTS"))).thenReturn(content);
		when(httpClient.get(new URL("http://localhost/faolexfi/kwid=089/iso3=ERROR"))).thenThrow(new RuntimeException("Get failed"));

		
		client = new MonikersRestClient("http://localhost/", httpClient);
	}

	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getRfb4Iso3(java.lang.String)}.
	 */
	@Test
	public void testGetRfb4Iso3() {
		List<Rfb> entries = client.getRfb4Iso3("ALB");
		
		assertNotNull(entries);
		assertFalse(entries.isEmpty());
	}
	
	@Test
	public void testGetRfb4Iso3MissingCountry() {
		List<Rfb> entries = client.getRfb4Iso3("NOT_EXISTS");
		
		assertNull(entries);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetRfb4Iso3ConnectionFail() {
		client.getRfb4Iso3("ERROR");
	}
	
	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getRfbByAcronym(java.lang.String)}.
	 */	
	@Test
	public void testGetRfbByAcronym() {
		Rfb entry = client.getRfbByAcronym("ICCAT");
		
		assertNotNull(entry);
		assertNotNull(entry.getFid());
	}
	
	@Test
	public void testGetRfbByAcronymMissingCountry() {
		Rfb entry = client.getRfbByAcronym("NOT_EXISTS");
		
		assertNull(entry);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetRfbByAcronymConnectionFail() {
		client.getRfbByAcronym("ERROR");
	}
	
	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getRfbByFid(java.lang.String)}.
	 */	
	@Test
	public void testGetRfbByFid() {
		Rfb entry = client.getRfbByFid("9294");
		
		assertNotNull(entry);
		assertNotNull(entry.getFid());
	}
	
	@Test
	public void testGetRfbByFidMissingCountry() {
		Rfb entry = client.getRfbByFid("NOT_EXISTS");
		
		assertNull(entry);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetRfbByFidConnectionFail() {
		client.getRfbByFid("ERROR");
	}


	/**
	 * Test method for {@link org.sharks.service.moniker.rest.MonikersRestClient#getFaoLexDocuments(java.lang.String)}.
	 */
	@Test
	public void testGetFaoLexDocuments() {
		List<FaoLexFiDocument> docs = client.getFaoLexDocuments("aus");
		
		assertNotNull(docs);
		assertFalse(docs.isEmpty());
	}
	
	@Test
	public void testGetFaoLexDocumentsNotFound() {
		List<FaoLexFiDocument> docs = client.getFaoLexDocuments("NOT_EXISTS");
		
		assertNull(docs);
	}
	
	@Test(expected=MonikersRestClientException.class)
	public void testGetFaoLexDocumentsConnectionError() {
		client.getFaoLexDocuments("ERROR");
	}

}
