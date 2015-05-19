/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_COUNTRY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.MeasureDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceImpl implements ManagementEntityService {
	
	//TODO is correct to espress it here?
	private final Long[] OTHER_TYPES = new Long[]{2l, 3l};
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private MeasureDao measureDao;
	
	@Inject
	private InformationSourceDao informationSourceDao;
	
	@Inject
	private MonikerService monikerService;
	
	@Override
	public EntityDetails get(String acronym) {
		MgmtEntity entity = dao.getByAcronym(acronym);
		if (entity == null) return null;
		
		String imageId = null;
		String website = null;
		List<CountryEntry> members = Collections.emptyList();
		
		FigisDoc doc = monikerService.getFigisDocByAcronym(acronym);
		if (doc!=null) {
			imageId = doc.getImageId();
			website = doc.getWebsite();
			members = convert(doc.getMembers(), TO_COUNTRY_ENTRY);
		}
		
		List<Measure> measures = measureDao.listRelatedToManagementEntityAcronym(acronym);
		
		List<InformationSource> others = informationSourceDao.listRelatedToEntity(entity.getCode(), OTHER_TYPES);
		
		return new EntityDetails(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName(),
				imageId,
				website,
				members,
				convert(measures, TO_MEASURE_ENTRY),
				convert(others, TO_ENTITY_DOCUMENT)
				);
	}

	@Override
	public List<EntityEntry> list() {
		return convert(dao.listRelatedToInformationSource(), TO_ENTITY_ENTRY);
	}
	
}
