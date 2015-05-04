/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.CustomSpeciesGroupService;
import org.sharks.storage.dao.CustomSpeciesGroupDao;
import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CustomSpeciesGrpServiceImpl implements CustomSpeciesGroupService {
	
	@Inject
	private CustomSpeciesGroupDao dao;

	@Override
	public List<CustomSpeciesGrp> list() {
		return dao.list();
	}

}
