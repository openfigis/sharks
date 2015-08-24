/**
 * 
 */
package org.sharks.service.cites.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="Row")
@XmlAccessorType(XmlAccessType.NONE)
public class CitesCountry {
	
	@XmlPath("Entry[@type='country']/@ISO3")
	private String iso3;
	
	@XmlPath("Entry[@type='country']/@ISO2")
	private String iso2;
	
	@XmlPath("Entry[@type='country']/text()")
	private String name;

}
