/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class RefPubSpecies implements Serializable {
	
	private static final long serialVersionUID = 4348215419856024299L;
	
	private final String scientificName;
	private final String ficItem;
	private final MultiLingualName longNames;

}
