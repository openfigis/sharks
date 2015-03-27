/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
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
	public List<MgmtEntity> list() {
		return dao.list();
	}

}
