/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class RefPubCountry implements Serializable {
	
	private static final long serialVersionUID = 2880082020170251510L;
	
	private final String unIso3Code;
	private final String unIso2Code;
	private final String continent;
	private final List<String> fisheryCommissions;
	private final MultiLingualName multilingualOfficialName;
	
}
