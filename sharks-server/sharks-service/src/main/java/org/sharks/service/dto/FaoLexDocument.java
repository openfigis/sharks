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
public class FaoLexDocument {
	
	private final String faolexId;
	private final String title;
	private final String description;
	private final Integer year;
	private final Integer yearOfConsolidation;
	private final String url;

}
