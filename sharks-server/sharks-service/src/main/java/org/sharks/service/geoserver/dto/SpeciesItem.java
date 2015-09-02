package org.sharks.service.geoserver.dto;

import java.io.Serializable;

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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SpeciesItem implements Serializable {
	
	private static final long serialVersionUID = 7678331428284389660L;

	@XmlAttribute(name="a3c")
	private String alphaCode;
	
	@XmlAttribute(name="FigisID")
	private String figisId;

}
