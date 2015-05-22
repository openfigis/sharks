/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.CountryEntity;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class CountryEntityProducer extends AbstractEntryProducer<String, CountryEntity> {
	
	@Inject
	private ManagementEntityDao dao;

	@Override
	public CountryEntity produce(String acronym) {
		boolean hasMeasures = false;
		MgmtEntity entity = dao.getByAcronym(acronym);
		if (entity!=null) hasMeasures = !entity.getMeasures().isEmpty();
		return new CountryEntity(acronym, hasMeasures);
	}

}
