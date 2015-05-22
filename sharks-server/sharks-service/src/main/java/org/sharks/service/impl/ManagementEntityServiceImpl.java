/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_COUNTRY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceImpl implements ManagementEntityService {
	
	@Inject
	private ManagementEntityDao dao;
	
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
		
		List<InformationSource> others = onlyOthersOrPoAs(entity.getInformationSources());
		
		return new EntityDetails(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName(),
				imageId,
				website,
				members,
				convert(entity.getMeasures(), TO_MEASURE_ENTRY),
				convert(others, TO_ENTITY_DOCUMENT)
				);
	}
	
	private List<InformationSource> onlyOthersOrPoAs(List<InformationSource> sources) {
		return sources.stream()
				.filter(
						source->source.getInformationType().getCode().equals(InformationSourceDao.POA_TYPE)
						|| source.getInformationType().getCode().equals(InformationSourceDao.OTHER_TYPE))
				.collect(Collectors.toList());
				
				
	}

	@Override
	public List<EntityEntry> list(boolean onlyWithMeasuresOrOthers) {
		return convert(dao.listRFMOs(onlyWithMeasuresOrOthers), TO_ENTITY_ENTRY);
	}
	
}
