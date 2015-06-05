package org.sharks.service.moniker;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.service.http.DefaultHttpClient;
import org.sharks.service.http.HttpClient;
import org.sharks.service.moniker.dto.FaoLexDocument;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class TestMonikersRestClient {
	
	private static MonikersRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		client = new MonikersRestClient("http://figisapps.fao.org/figis/moniker/", httpClient);
	}

	@Test @Ignore
	public void testGetRfb4Iso3() {
		List<RfbEntry> rfbs = client.getRfb4Iso3("USA");
		assertNotNull(rfbs);
		System.out.println(rfbs);
	}
	
	@Test @Ignore
	public void testGetRfb() {
		RfbEntry rfb = client.getRfb("ICCAT");
		assertNotNull(rfb);
		System.out.println(rfb);
	}
	
	@Test @Ignore
	public void testGetFigisDoc() {
		FigisDoc doc = client.getFigisDoc("9294");
		assertNotNull(doc);
		System.out.println(doc);
	}
	
	@Test @Ignore
	public void testGetFaoLexDocuments() {
		List<FaoLexDocument> docs = client.getFaoLexDocuments("AUS");
				
		assertNotNull(docs);
		System.out.println(docs);
	}


}
