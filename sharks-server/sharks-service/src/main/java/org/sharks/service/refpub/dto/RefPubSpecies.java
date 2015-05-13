/**
 * 
 */
package org.sharks.service.refpub.dto;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class RefPubSpecies {
	
	private final String scientificName;
	private final String ficItem;
	private final MultiLingualName longNames;

}
