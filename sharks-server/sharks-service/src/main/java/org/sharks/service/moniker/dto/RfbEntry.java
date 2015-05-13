/**
 * 
 */
package org.sharks.service.moniker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="rfb")
@XmlAccessorType(XmlAccessType.NONE)
public class RfbEntry {
	
	@XmlAttribute
	private String name;
	
	@XmlAttribute(name="FigisID")
	private String figisId;
	
	@XmlAttribute(name="MetaID")
	private String metaId;
	
	@XmlAttribute
	private String lang;
	
	@XmlAttribute(name="URI")
	private String uri;
	
}
