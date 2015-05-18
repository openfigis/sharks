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
public class EntityDetails {
	
	private final long id;
	private final String acronym;
	private final String name;
	private final String imageId;
	private final String webSite;
	private final List<CountryEntry> members;
	private final List<MeasureEntry> measures;
	

}
