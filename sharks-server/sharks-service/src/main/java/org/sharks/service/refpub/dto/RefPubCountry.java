/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="concept",namespace="http://www.fao.org/fi/refpub/webservice")
@XmlAccessorType(XmlAccessType.NONE)
public class RefPubCountry {
	
	@XmlElement(name="multilingualName")
	private MultiLingualName multilingualName;
	
	@XmlElement(name="multilingualLongName")
	private MultiLingualName multilingualLongName;
	
	@XmlElement(name="multilingualOfficialName")
	private MultiLingualName multilingualOfficialName;
	
	@XmlElement
	private Codes codeList;
	
	public Code getUnIso3Code() {
		return findCode("UN-ISO3");
	}
	
	private Code findCode(String name) {
		return codeList.getCodes().stream().filter(code->code.getName().equals(name)).findFirst().orElse(null);
	}
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Codes {
		
		@XmlElement(name="code")
		private List<Code> codes;
	}
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Code {
		
		@XmlAttribute
		private String name;
		
		@XmlAttribute
		private String concept;
	}
}
