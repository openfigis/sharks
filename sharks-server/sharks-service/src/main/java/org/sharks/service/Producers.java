/**
 * 
 */
package org.sharks.service;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.indexing.IndexingService;
import org.sharks.service.indexing.SolrDocumentProviders;
import org.sharks.service.indexing.SolrIndexingService;
import org.sharks.service.indexing.SolrIndexingService.SolrDocumentProvider;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Slf4j
public class Producers {
	
	@Produces
	public RefPubRestClient getRefPubRestClient(Configuration configuration) {
		return new RefPubRestClient(configuration.getRefPubUrl());
	}
	
	@Produces
	public MonikersRestClient getMonikerRestClient(Configuration configuration) {
		return new MonikersRestClient(configuration.getMonikersUrl());
	}
	
	@Produces
	public IndexingService getIndexingService(Configuration configuration) {
		if (configuration.getSolrUrl() == null) {
			log.warn("Solr Url missing from configuration");
			return null;
		}
		
		List<SolrDocumentProvider<?>> providers = Arrays.asList(SolrDocumentProviders.MEASURE, SolrDocumentProviders.POA);
		log.trace("providers:");
		for (SolrDocumentProvider<?> provider:providers) log.trace("provider for {}",provider.getType().getSimpleName());
		
		try {
			return new SolrIndexingService(configuration.getSolrUrl(), providers);
		} catch(Exception e) {
			log.error("Solar indexing service failed", e);
			return null;
		}
	}

}
