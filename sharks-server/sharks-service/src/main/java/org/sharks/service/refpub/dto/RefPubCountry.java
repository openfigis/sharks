/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.List;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
public class RefPubCountry {
	
	private final String unIso3Code;
	private final String continent;
	private final List<String> fisheryCommissions;
	private final MultiLingualName multilingualOfficialName;
	
}
