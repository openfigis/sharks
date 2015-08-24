/**
 * 
 */
package org.sharks.service.dto;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class SpeciesEntry {

	private final String alphaCode;
	private final String scientificName;
	private final String englishName;
	private final String frenchName;
	private final String spanishName;
	private final String arabicName;
	private final String chineseName;
	private final String russianName;
	private final boolean hasMeasures;
}
