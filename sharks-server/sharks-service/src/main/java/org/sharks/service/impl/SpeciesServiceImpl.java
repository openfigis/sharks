/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.SpeciesService;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesServiceImpl implements SpeciesService {
	
	@Inject
	private SpeciesDao dao;

	@Override
	public List<Species> list() {
		return dao.list();
	}

	@Override
	public Species getSpecies(String code) {
		return dao.get(code);
	}

}
