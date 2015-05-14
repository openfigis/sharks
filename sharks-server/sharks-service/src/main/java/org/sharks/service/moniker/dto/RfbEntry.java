/**
 * 
 */
package org.sharks.service.moniker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="rfb")
@XmlAccessorType(XmlAccessType.NONE)
public class RfbEntry {
	
	@XmlAttribute(name="FigisID")
	private String figisId;
	
}
