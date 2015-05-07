/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.SpeciesComplementService;
import org.sharks.service.SpeciesService;
import org.sharks.service.dto.SpeciesDetails;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesServiceImpl implements SpeciesService {
	
	@Inject
	private SpeciesDao dao;
	
	@Inject
	private SpeciesComplementService complementService; 

	@Override
	public List<Species> list(boolean onlyWithMeasure) {
		return onlyWithMeasure?dao.listWithMeasures():dao.list();
	}

	@Override
	public SpeciesDetails getSpecies(String alpha3Code) {
		Species species = dao.getByAlphaCode(alpha3Code);
		return complementService.complement(species);
	}

}
