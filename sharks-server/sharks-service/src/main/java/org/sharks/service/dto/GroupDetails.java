/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class GroupDetails {
	
	private final Long code;
	private final String name;
	private final String note;
	private final List<SpeciesEntry> species;
	private final List<MeasureEntry> measures;

}
