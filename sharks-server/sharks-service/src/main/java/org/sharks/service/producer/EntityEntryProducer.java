/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.EntityEntry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.storage.dao.InformationSourceDao;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class EntityEntryProducer extends AbstractEntryProducer<String, EntityEntry> {
	
	@Inject
	private InformationSourceDao sourcesDao;

	@Override
	public EntityEntry produce(String acronym) {
		boolean hasInformationSources = sourcesDao.existsRelatedToEntityByAcronym(acronym);
		return new EntityEntry(acronym, hasInformationSources);
	}

}
