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

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class SpeciesComplementService {
	
	public static final String NOT_AVAILABLE_CODE = "N/A"; 
	
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
		return new SpeciesDetails(species.getAlphaCode(), species.getScientificName(), officialNames);		
	}
	
}
