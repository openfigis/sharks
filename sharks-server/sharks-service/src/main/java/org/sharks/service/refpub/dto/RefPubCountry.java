/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.sharks.service.refpub.dto.RefPubConcept.Code;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubCountry {
	
	private RefPubConcept concept;
	
	public RefPubCountry(RefPubConcept concept) {
		this.concept = concept;
	}
	
	public Code getUnIso3Code() {
		return concept.findCode("UN-ISO3");
	}

	public List<String> getFisheryCommissions() {
		return concept.findParents("Fishery commission").stream().map(parent -> parent.getMultilingualName().getEnglish()).collect(Collectors.toList());
	}

	public MultiLingualName getMultilingualOfficialName() {
		return concept.getMultilingualOfficialName();
	}
	
	public String getContinent() {
		return concept.findParents("Continent").stream().map(parent -> parent.getMultilingualName().getEnglish()).findFirst().orElse(null);
	}
	
}
