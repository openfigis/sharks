/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.sharks.service.refpub.dto.RefPubConcept.Attribute;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class RefPubConverter {
	
	public RefPubSpecies toSpecies(RefPubConcept concept) {
		Attribute ficItem = concept.findAttribute("FIC_ITEM"); 
		return new RefPubSpecies(concept.getScientificName(), ficItem!=null?ficItem.getValue():null, concept.getMultilingualLongName());
	}
	
	public RefPubCountry toCountry(RefPubConcept concept) {
		String continent = concept.findParents("Continent").stream().map(parent -> parent.getMultilingualName().getEnglish()).findFirst().orElse(null);
		List<String> fisheryCommissions = concept.findParents("Fishery commission").stream().map(parent -> parent.getMultilingualName().getEnglish()).collect(Collectors.toList());
		return new RefPubCountry(concept.findCode("UN-ISO3"), continent, fisheryCommissions, concept.getMultilingualOfficialName());
	}

}
