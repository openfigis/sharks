/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.EntityDetails;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityServiceImp implements ManagementEntityService {
	
	@Inject
	private ManagementEntityDao dao;

	@Override
	public List<EntityDetails> list() {
		return dao.list().stream().map(e->toDetails(e)).collect(Collectors.toList());
	}
	
	private EntityDetails toDetails(MgmtEntity entity) {
		if (entity == null) return null;
		return new EntityDetails(entity.getCode(), 
				entity.getAcronym(), 
				entity.getMgmtEntityName());
	}


}
