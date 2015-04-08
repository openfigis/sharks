/**
 * 
 */
package org.sharks.service.indexing;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.event.ApplicationEvent;
import org.sharks.storage.dao.CountryDao;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class IndexingManager {
	
	@Inject
	private IndexingService indexingService;
	
	@Inject
	private CountryDao countryDao;
	
	void startIndexing(@Observes ApplicationEvent.Startup startup) {
		
		if (indexingService == null) {
			log.warn("Indexing service not available");
			return;
		}
		
		log.info("starting indexing");
		
		log.trace("deleting all documents");
		indexingService.deleteAllDocuments();
		
		log.trace("indexing countries...");
		indexingService.index(countryDao.list(), FieldProviders.byReflection(Country.class));
		
		log.info("indexing complete.");
	}

}
