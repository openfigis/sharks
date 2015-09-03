/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;
import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.dto.EntityMember;
import org.sharks.service.informea.dto.InformeaCountry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@Singleton
public class InformeaEntityMemberProducer extends AbstractEntryProducer<InformeaCountry, EntityMember> {
	
	@Inject
	private ManagementEntityDao dao;
	
	@Inject
	private RefPubService refPub;

	@Override
	public EntityMember produce(InformeaCountry country) {
		
		boolean hasPoAs = false;
		String name = null;
		
		String iso3code = country.getIso3();
		
		if (iso3code == null) {
			RefPubCountry refPubCountry = refPub.getCountryByIso2(country.getIso2());
			if (refPubCountry!=null) iso3code = refPubCountry.getUnIso3Code();
		}
		
		MgmtEntity entity = dao.getByAcronym(iso3code);
		if (entity!=null) {
			hasPoAs = !entity.getPoAs().isEmpty();
			name = entity.getMgmtEntityName();
		} else {
			name = country.getName();
			log.warn("Unknown country "+country);
		}
		
		return new EntityMember(iso3code, name, hasPoAs);
	}

}
