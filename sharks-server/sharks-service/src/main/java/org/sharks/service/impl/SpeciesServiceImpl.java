/**
 * 
 */
package org.sharks.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.sharks.service.SpeciesService;
import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.service.producer.SpeciesEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Species;

import static org.sharks.service.producer.EntryProducers.*;
import static org.sharks.service.util.ConversionUtil.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesServiceImpl implements SpeciesService {
	
	@Inject
	private SpeciesDao dao;
	
	@Inject
	private SpeciesEntryProducer speciesEntryProducer;
	
	@Inject
	private RefPubService refPubService;
	
	@Override
	public SpeciesDetails getSpecies(String alpha3Code) {
		
		//TODO cache
		
		Species species = dao.getByAlphaCode(alpha3Code);
		if (species == null) return null;
		
		String scientificName = species.getScientificName();
		Map<String, String> officialNames = Collections.emptyMap();
		
		RefPubSpecies refPubSpecies = refPubService.getSpecies(species.getAlphaCode());
		if (refPubSpecies!=null) {
			scientificName = refPubSpecies.getScientificName();
			officialNames = toNamesMap(refPubSpecies.getLongNames());
		}
		
		return new SpeciesDetails(alpha3Code, 
				scientificName, 
				officialNames,
				convert(species.getMeasures(), TO_MEASURE_ENTRY));
	}

	@Override
	public List<SpeciesEntry> list(boolean onlyWithMeasure) {
		
		//TODO cache
		
		List<Species> species = onlyWithMeasure?dao.listWithMeasures():dao.list();
		return convert(species, speciesEntryProducer);
	}
}
