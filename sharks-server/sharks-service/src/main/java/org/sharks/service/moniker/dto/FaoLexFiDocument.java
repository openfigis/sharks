/**
 * 
 */
package org.sharks.service.moniker.dto;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.sharks.service.moniker.rest.CalendarAdapter;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="document")
@XmlAccessorType(XmlAccessType.NONE)
public class FaoLexFiDocument {
	
	@XmlAttribute
	private String uri;
	
	@XmlPath("field[@name='Title_of_Text']/@value")
	private String title;
	
	@XmlPath("field[@name='Long_Title_of_text']/@value")
	private String longTitle;
	
	@XmlPath("field[@name='Date_of_Text']/@value")
	@XmlJavaTypeAdapter(CalendarAdapter.class)
	private Calendar dateOfText;
	
	@XmlPath("field[@name='Date_of_original_Text']/@value")
	@XmlJavaTypeAdapter(CalendarAdapter.class)
	private Calendar dateOfOriginalText;
	
	@XmlPath("field[@name='Date_of_Consolidation']/@value")
	@XmlJavaTypeAdapter(CalendarAdapter.class)
	private Calendar dateOfConsolidation;
	
	@XmlPath("field[@name='FaolexId']/@value")
	private String faolexId;

}
