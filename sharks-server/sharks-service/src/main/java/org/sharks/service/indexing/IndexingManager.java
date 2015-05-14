/**
 * 
 */
package org.sharks.service.indexing;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.event.ApplicationEvent;
import org.sharks.storage.dao.MeasureDaoImpl;
import org.sharks.storage.dao.PoADao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class IndexingManager {
	
	@Inject
	private IndexingService indexingService;
	
	@Inject
	private MeasureDaoImpl measureDao;
	
	@Inject
	private PoADao poaDao;
	
	void startIndexing(@Observes ApplicationEvent.Startup startup) {
		
		if (indexingService == null) {
			log.warn("Indexing service not available");
			return;
		}
		
		log.info("starting indexing");
		
		log.trace("deleting all documents");
		indexingService.deleteAllDocuments();
		
		log.trace("indexing measures...");
		indexingService.index(measureDao.list(), Measure.class);
		
		log.trace("indexing poas...");
		indexingService.index(poaDao.list(), PoA.class);
		
		log.info("indexing complete.");
	}

}
