/**
 * 
 */
package org.sharks.service.kor.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="mappings")
@XmlAccessorType(XmlAccessType.NONE)
public class KorMappingList {
	
	@XmlPath("mapping")
	private List<KorMapping> mappings;
	
	@Data
	@XmlRootElement(name="mapping")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class KorMapping {
		
		@XmlAttribute
		private String acronym;
		@XmlAttribute
		private String id;
		

	}
	

}
