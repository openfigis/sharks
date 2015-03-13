/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.SharksService;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SharksServiceImpl implements SharksService {
	
	@Inject
	private SpeciesDao speciesDao;

	@Override
	public List<Species> listAllSpecies() {
		return speciesDao.list();
	}

	@Override
	public Species getSpecies(String code) {
		return speciesDao.get(code);
	}

}
