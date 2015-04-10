/**
 * 
 */
package org.sharks.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceImp implements ManagementEntityService {
	
	@Inject
	private ManagementEntityDao dao;

	@Override
	public List<EntityEntry> list() {
		return dao.list().stream().map(e->toEntry(e)).collect(Collectors.toList());
	}
	
	private EntityEntry toEntry(MgmtEntity entity) {
		if (entity == null) return null;
		return new EntityEntry(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName());
	}

	@Override
	public List<Country> getCountries(String acronym) {
		return Collections.emptyList();
	}

	@Override
	public List<EntityEntry> getEntitiesForCountry(String countryCode) {
		return Collections.emptyList();
	}


}
