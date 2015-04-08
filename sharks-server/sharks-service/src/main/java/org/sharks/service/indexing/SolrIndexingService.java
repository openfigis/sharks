/**
 * 
 */
package org.sharks.service.indexing;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SolrIndexingService implements IndexingService {
	
	private SolrClient solr;
	
	public SolrIndexingService(String clientUri) {
		solr = new HttpSolrClient(clientUri);
	}

	@Override
	public <T> void index(List<T> items, FieldProvider<T> fieldProvider) {
		
		List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
		
		for (T item:items) {
			SolrInputDocument document = createDocument(item, fieldProvider);
			documents.add(document);
		}
		
		try {
			solr.add(documents);
			solr.commit();
		} catch (Exception e) {
			throw new RuntimeException("Failed indexing documents", e);
		}
	}
	
	public void deleteAllDocuments() {
		try {
			solr.deleteByQuery("*:*");
			solr.commit();
		} catch (Exception e) {
			throw new RuntimeException("Failed deleting all documents", e);
		}
	}
	
	private <T> SolrInputDocument createDocument(T item, FieldProvider<T> fieldProvider) {
		SolrInputDocument document = new SolrInputDocument();
		
		document.addField("documentType", fieldProvider.getTypeName());
		
		for (String fieldName:fieldProvider.getFieldsName()) {
			String fieldValue = fieldProvider.getFieldValue(item, fieldName);
			document.addField(fieldName, fieldValue);
		}
		
		return document;
	}
	
}
