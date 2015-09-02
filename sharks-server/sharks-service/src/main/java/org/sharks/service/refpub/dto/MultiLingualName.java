/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class MultiLingualName implements Serializable {
	
	private static final long serialVersionUID = 2161184315595293377L;

	@XmlElement(name="EN")
	private String english;
	
	@XmlElement(name="FR")
	private String french;

	@XmlElement(name="ES")
	private String spanish;
	
	@XmlElement(name="AR")
	private String arabic;
	
	@XmlElement(name="ZH")
	private String chinese;
	
	@XmlElement(name="RU")
	private String russian;

}
