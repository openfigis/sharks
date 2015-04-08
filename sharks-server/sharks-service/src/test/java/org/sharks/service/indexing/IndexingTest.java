/**
 * 
 */
package org.sharks.service.indexing;

import java.util.Arrays;
import java.util.Collections;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class IndexingTest {
	
	private static SolrIndexingService indexingService;
	
	@BeforeClass
	public static void setupService() {
		indexingService = new SolrIndexingService("http://localhost:8983/solr/sharks", Arrays.asList(SolrDocumentProviders.MEASURE, SolrDocumentProviders.POA));
	}
	
	@Test @Ignore
	public void testIndexing() {
		
		
		PoA poa = new PoA();
		poa.setCode(1l);
		poa.setTitle("My Poa");
		
		indexingService.deleteAllDocuments();
		indexingService.index(Collections.<PoA>singletonList(poa), PoA.class);

	}
	
}
