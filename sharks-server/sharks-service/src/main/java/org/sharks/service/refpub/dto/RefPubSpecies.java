/**
 * 
 */
package org.sharks.service.refpub.dto;

import org.sharks.service.refpub.dto.RefPubConcept.Attribute;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class RefPubSpecies {
	
	private RefPubConcept concept;
	
	public RefPubSpecies(RefPubConcept concept) {
		this.concept = concept;
	}
	
	public Attribute getFicItem() {
		return concept.findAttribute("FIC_ITEM");
	}
	
	public MultiLingualName getLongNames() {
		return concept.getMultilingualLongName();
	}

}
