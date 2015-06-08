/**
 * 
 */
package org.sharks.service.moniker.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import org.sharks.service.moniker.rest.MonikersParser.DateAdapter;

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
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dateOfText;
	
	@XmlPath("field[@name='Date_of_Consolidation']/@value")
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date dateOfConsolidation;
	
	@XmlPath("field[@name='FaolexId']/@value")
	private String faolexId;

}
