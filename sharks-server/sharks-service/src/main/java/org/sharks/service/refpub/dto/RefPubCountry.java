/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.List;

import lombok.Data;

import org.sharks.service.refpub.dto.RefPubConcept.Code;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class RefPubCountry {
	
	private final Code unIso3Code;
	private final String continent;
	private final List<String> fisheryCommissions;
	private final MultiLingualName multilingualOfficialName;
	
}
