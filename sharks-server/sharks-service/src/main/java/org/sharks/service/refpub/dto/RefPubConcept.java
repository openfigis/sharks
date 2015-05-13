/**
 * 
 */
package org.sharks.service.refpub.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="concept",namespace="http://www.fao.org/fi/refpub/webservice")
@XmlAccessorType(XmlAccessType.NONE)
public class RefPubConcept {
	
	@XmlElement(name="multilingualName")
	private MultiLingualName multilingualName;
	
	@XmlElement(name="multilingualLongName")
	private MultiLingualName multilingualLongName;
	
	@XmlElement(name="multilingualOfficialName")
	private MultiLingualName multilingualOfficialName;
	
	@XmlElement
	private Codes codeList;
	
	@XmlElement
	private Hierarchy hierarchy;
	
	@XmlElement(name="attr")
	private Attributes attributes;
	
	//Species specific
	@XmlElement(name="scientific_name")
	private String scientificName;
	
	public Code findCode(String name) {
		return codeList.getCodes().stream().filter(code->code.getName().equals(name)).findFirst().orElse(null);
	}
	
	public List<Parent> findParents(String name) {
		if (hierarchy == null 
				|| hierarchy.getParents() == null 
				|| hierarchy.getParents().getItems() == null 
				|| hierarchy.getParents().getItems().isEmpty()) return Collections.emptyList();
		return hierarchy.getParents().getItems().stream().filter(parent->parent.getType().equals(name)).collect(Collectors.toList());
	}
	
	
	public Attribute findAttribute(String name) {
		return attributes.getAttributes().stream().filter(attribute->attribute.getName().equals(name)).findFirst().orElse(null);
	}
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Attributes {
		
		@XmlElement(name="value")
		private List<Attribute> attributes;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@XmlRootElement(name="value")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Attribute {
		
		@XmlAttribute
		private String name;
		
		@XmlValue
		private String value;
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
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Hierarchy {
		
		@XmlElement
		private Parents parents;
	}
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Parents {
		
		@XmlElement(name="parent")
		private List<Parent> items;
	}
	
	@Data
	@XmlRootElement
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Parent {
		
		@XmlAttribute(name="type")
		private String type;
		
		@XmlElement(name="multilingualName")
		private MultiLingualName multilingualName;
		
		@XmlElement(name="multilingualLongName")
		private MultiLingualName multilingualLongName;
		
		@XmlElement(name="multilingualOfficialName")
		private MultiLingualName multilingualOfficialName;
	}
}
