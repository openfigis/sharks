/**
 * 
 */
package org.sharks.service.moniker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="error")
@XmlAccessorType(XmlAccessType.NONE)
public class ErrorElement {
	
	@XmlValue
	String text;

}
