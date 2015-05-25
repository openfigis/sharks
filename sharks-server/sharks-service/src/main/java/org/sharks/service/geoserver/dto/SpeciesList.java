/**
 * 
 */
package org.sharks.service.geoserver.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="root")
@XmlAccessorType(XmlAccessType.NONE)
public class SpeciesList {
	
	@XmlElement(name="item")
	private List<SpeciesItem> items;

}
