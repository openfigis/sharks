/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cites.dto.CitesCountry;
import org.sharks.service.dto.EntityMember;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton
public class CitesEntityMemberProducer extends AbstractEntryProducer<CitesCountry, EntityMember> {
	
	@Inject
	ManagementEntityDao dao;

	@Override
	public EntityMember produce(CitesCountry country) {
		
		boolean hasPoAs = false;
		String name = null;
		
		MgmtEntity entity = dao.getByAcronym(country.getIso3());
		if (entity!=null) {
			hasPoAs = !entity.getPoAs().isEmpty();
			name = entity.getMgmtEntityName();
		} else {
			name = country.getName();
			log.warn("Unknown country "+country);
		}
		
		return new EntityMember(country.getIso3(), name, hasPoAs);
	}

}
