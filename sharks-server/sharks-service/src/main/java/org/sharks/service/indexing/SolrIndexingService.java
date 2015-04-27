/**
 * 
 */
package org.sharks.service.indexing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class SolrIndexingService implements IndexingService {
	
	private SolrClient solr;
	private Map<Class<?>,SolrDocumentProvider<?>> indexedProviders;
	
	public SolrIndexingService(String clientUri, Iterable<SolrDocumentProvider<?>> providers) {
		solr = new HttpSolrClient(clientUri);
		
		indexedProviders = new HashMap<Class<?>, SolrDocumentProvider<?>>();
		for (SolrDocumentProvider<?> provider:providers) {
			indexedProviders.put(provider.getType(), provider);
		}
	}
	
	@SuppressWarnings("unchecked")
	private <T> SolrDocumentProvider<T> getProvider(Class<T> type) {
		return (SolrDocumentProvider<T>) indexedProviders.get(type);
	}
	
	@Override
	public <T> void index(List<T> items, Class<T> type) {
		
		List<SolrInputDocument> documents = new ArrayList<SolrInputDocument>();
		
		SolrDocumentProvider<T> provider = getProvider(type);
		
		for (T item:items) {
			SolrInputDocument document = provider.getDocument(item);
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
		log.trace("deleteAllDocuments");
		try {
			solr.deleteByQuery("*:*");
			solr.commit();
		} catch (Exception e) {
			log.error("Failed deleting all indexed documents", e);
			throw new RuntimeException("Failed deleting all documents", e);
		}
	}
	
	public interface SolrDocumentProvider<T> {
		
		public Class<T> getType();
		
		public SolrInputDocument getDocument(T item);
	}
	
}
