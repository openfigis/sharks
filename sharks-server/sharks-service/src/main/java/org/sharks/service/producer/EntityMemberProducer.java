/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.EntityMember;
import org.sharks.service.moniker.dto.RfbEntry.RfbMember;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class EntityMemberProducer extends AbstractEntryProducer<RfbMember, EntityMember> {
	
	@Inject
	ManagementEntityDao dao;

	@Override
	public EntityMember produce(RfbMember member) {
		
		boolean hasPoAs = false;
		
		MgmtEntity entity = dao.getByAcronym(member.getIso3());
		if (entity!=null) hasPoAs = !entity.getPoAs().isEmpty();
		
		return new EntityMember(member.getIso3(), member.getEnglishName(), hasPoAs);
	}

}
