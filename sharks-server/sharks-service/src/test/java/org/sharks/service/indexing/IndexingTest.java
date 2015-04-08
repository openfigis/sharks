/**
 * 
 */
package org.sharks.service.indexing;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class IndexingTest {
	
	@Test @Ignore
	public void testIndexing() {
		SolrIndexingService indexingService = new SolrIndexingService("http://localhost:8983/solr/sharks");
		
		Country country = new Country();
		country.setCode("123");
		country.setUnName("France");
		
		indexingService.deleteAllDocuments();
		indexingService.index(Collections.<Country>singletonList(country), FieldProviders.<Country>byReflection(Country.class));
		
		
	}
	
}
