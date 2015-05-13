/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_ENTITY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.MeasureDao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceImp implements ManagementEntityService {
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private MeasureDao measureDao;
	

	@Override
	public EntityDetails get(String acronym) {
		MgmtEntity entity = dao.getByAcronym(acronym);
		
		List<Measure> measures = measureDao.listRelatedToManagementEntityAcronym(acronym);
		
		return new EntityDetails(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName(),
				convert(measures, TO_MEASURE_ENTRY)
				);
	}

	@Override
	public List<EntityEntry> list() {
		return convert(dao.list(), TO_ENTITY_ENTRY);
	}
	
}
