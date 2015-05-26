/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class SpeciesDetails {

	private final String alphaCode;
	private final String scientificName;
	private final String note;
	private final String figisId;
	private final boolean hasDistributionMap;
	private final Map<String, String> officialNames;
	private final List<MeasureEntry> measures;
}
