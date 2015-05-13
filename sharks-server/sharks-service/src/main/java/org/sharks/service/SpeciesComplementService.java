/**
 * 
 */
package org.sharks.service;

import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.SpeciesDetails;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.util.ComplementUtil;
import org.sharks.storage.domain.Species;

import static org.sharks.service.EntryConverters.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class SpeciesComplementService {
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private ComplementUtil util;

	public SpeciesDetails complement(Species species) {
		
		RefPubSpecies refPubSpecies = refPubService.getSpecies(species.getAlphaCode());
		return complement(species, refPubSpecies);
	}
	
	private SpeciesDetails complement(Species species, RefPubSpecies refPubSpecies) {
		Map<String,String> officialNames = refPubSpecies!=null?util.toNameMap(refPubSpecies.getLongNames()):Collections.emptyMap();
		return new SpeciesDetails(species.getAlphaCode(), 
				species.getScientificName(), 
				officialNames,
				convert(species.getMeasures(), TO_MEASURE_ENTRY));		
	}
	
}
