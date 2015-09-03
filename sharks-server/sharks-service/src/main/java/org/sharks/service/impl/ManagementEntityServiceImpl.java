/**
 * 
 */
package org.sharks.service.impl;

import static org.sharks.service.producer.EntryProducers.TO_ENTITY_DOCUMENT;
import static org.sharks.service.producer.EntryProducers.TO_ENTITY_ENTRY;
import static org.sharks.service.producer.EntryProducers.TO_MEASURE_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;
import static org.sharks.service.util.MeasuresUtil.filterReplacedAndHiddenMeasures;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.Service;
import org.sharks.service.Service.ServiceType;
import org.sharks.service.cache.Cached;
import org.sharks.service.cites.CitesService;
import org.sharks.service.cms.CmsService;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.EntityMember;
import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.moniker.MonikerService;
import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.producer.InformeaEntityMemberProducer;
import org.sharks.service.producer.EntityMemberProducer;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
@Service(name="managemententity",type=ServiceType.INTERNAL)
public class ManagementEntityServiceImpl implements ManagementEntityService {
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private MonikerService monikerService;
	
	@Inject
	private EntityMemberProducer memberProducer;
	
	@Inject
	private CitesService citesService;
	
	@Inject
	private InformeaEntityMemberProducer informeaMemberProducer;
	
	@Inject
	private CmsService cmsService;
	
	@Override @Cached("get")
	public EntityDetails get(String acronym) {
		
		MgmtEntity entity = dao.getByAcronym(acronym);
		if (entity == null) return null;
		
		String logoUrl = null;
		String website = null;
		String factsheetUrl = null;
		
		Rfb rfb = monikerService.getRfb(acronym);
		if (rfb!=null) {
			logoUrl = rfb.getLogo();
			website = rfb.getWebsite();
			factsheetUrl = rfb.getLink();
		}
		
		List<EntityMember> members = getMembers(acronym);
		
		List<InformationSource> others = onlyOthersOrPoAs(entity.getInformationSources());
		
		return new EntityDetails(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName(),
				entity.getMgmtEntityType().getCode(),
				logoUrl,
				website,
				factsheetUrl,
				members,
				convert(filterReplacedAndHiddenMeasures(entity.getMeasures()), TO_MEASURE_ENTRY),
				convert(others, TO_ENTITY_DOCUMENT)
				);
	}
	
	private List<EntityMember> getMembers(String acronym) {
		
		List<EntityMember> members = Collections.emptyList();
		
		switch (acronym.toUpperCase()) {
			case "CITES": members = getCitesMembers(); break;
			case "CMS": members = getCmsMembers(); break;
			default:{
				Rfb rfb = monikerService.getRfb(acronym);
				if (rfb!=null) members = convert(rfb.getMembers(), memberProducer);
			} break;
		}

		return members;
	}
	
	private List<EntityMember> getCitesMembers() {
		List<InformeaCountry> parties = citesService.getParties();
		List<EntityMember> members = convert(parties, informeaMemberProducer);
		return members;
	}
	
	private List<EntityMember> getCmsMembers() {
		List<InformeaCountry> parties = cmsService.getParties();
		List<EntityMember> members = convert(parties, informeaMemberProducer);
		return members;
	}
	
	private List<InformationSource> onlyOthersOrPoAs(List<InformationSource> sources) {
		return sources.stream()
				.filter(
						source->source.getInformationType().getCode().equals(InformationSourceDao.POA_TYPE)
						|| source.getInformationType().getCode().equals(InformationSourceDao.OTHER_TYPE))
				.collect(Collectors.toList());
	}

	@Override @Cached("list")
	public List<EntityEntry> list(boolean onlyWithMeasures) {
		return convert(dao.listRFMOsAndInstitutions(onlyWithMeasures, false), TO_ENTITY_ENTRY);
	}
	
}
