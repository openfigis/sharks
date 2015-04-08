/**
 * 
 */
package org.sharks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.sharks.config.Configuration;
import org.sharks.service.indexing.IndexingService;
import org.sharks.service.indexing.SolrIndexingService;
import org.sharks.service.moniker.rest.MonikersRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
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
		return new SolrIndexingService(configuration.getSolrUrl());
	}

}
