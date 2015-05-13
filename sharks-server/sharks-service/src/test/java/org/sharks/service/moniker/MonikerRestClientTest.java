package org.sharks.service.moniker;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MonikerRestClientTest {
	
	private static MonikersRestClient client;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		client = new MonikersRestClient("http://figisapps.fao.org/figis/moniker/");
	}

	@Test @Ignore
	public void testGetRfbs() {
		List<RfbEntry> rfbs = client.getRfbs("USA");
		assertNotNull(rfbs);
		System.out.println(rfbs);
	}


}
