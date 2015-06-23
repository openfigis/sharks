/**
 * 
 */
package org.sharks.service.dto;

import java.util.Calendar;

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
	private final Calendar dateOfText;
	private final Calendar dateOfOriginalText;
	private final Calendar dateOfConsolidation;
	private final String url;

}
