/**
 * 
 */
package org.sharks.service.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.SpeciesService;
import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.dto.SpeciesEntry;
import org.sharks.service.geoserver.GeoServerService;
import org.sharks.service.producer.SpeciesEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.dao.SpeciesDao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.Species;

import static org.sharks.service.producer.EntryProducers.*;
import static org.sharks.service.util.ConversionUtil.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class SpeciesServiceImpl implements SpeciesService {
	
	@Inject
	private SpeciesDao dao;
	
	@Inject
	private SpeciesEntryProducer speciesEntryProducer;
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private GeoServerService geoServerService;
	
	@Override
	public SpeciesDetails getSpecies(String alpha3Code) {
		
		//TODO cache
		
		Species species = dao.getByAlphaCode(alpha3Code);
		if (species == null) return null;
		
		String scientificName = species.getScientificName();
		Map<String, String> officialNames = Collections.emptyMap();
		String figisId = null;
		
		RefPubSpecies refPubSpecies = refPubService.getSpecies(species.getAlphaCode());
		if (refPubSpecies!=null) {
			scientificName = refPubSpecies.getScientificName();
			officialNames = toNamesMap(refPubSpecies.getLongNames());
			figisId = refPubSpecies.getFicItem();
		}
		
		boolean hasDistributionMap = geoServerService.hasSpeciesDistributionMap(alpha3Code);
		
		Set<Measure> measures = extractMeasures(species);
		
		return new SpeciesDetails(alpha3Code, 
				scientificName,
				figisId,
				hasDistributionMap,
				officialNames,
				convert(measures, TO_MEASURE_ENTRY));
	}
	
	private Set<Measure> extractMeasures(Species species) {
		Set<Measure> measures = new HashSet<Measure>(species.getMeasures());
		
		species.getCustomSpeciesGrps().forEach((group)->measures.addAll(group.getMeasures()));
		
		return measures;
	}
	

	@Override
	public List<SpeciesEntry> list(boolean onlyWithMeasure) {
		List<Species> species = onlyWithMeasure?dao.listWithMeasures():dao.list();
		return convert(species, speciesEntryProducer);
	}
}
