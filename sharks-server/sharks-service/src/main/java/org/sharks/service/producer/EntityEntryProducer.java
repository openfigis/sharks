/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.EntityEntry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class EntityEntryProducer extends AbstractEntryProducer<String, EntityEntry> {
	
	@Inject
	private ManagementEntityDao dao;
	
	//TODO cache

	@Override
	public EntityEntry produce(String acronym) {
		MgmtEntity entity = dao.getByAcronym(acronym);
		return new EntityEntry(entity!=null?entity.getCode():null, acronym);
	}

}
