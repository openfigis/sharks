/**
 * 
 */
package org.sharks.service.moniker.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

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
	
	@XmlAttribute(name="fid")
	private String fid;
	
	@XmlPath("members/arrayitem")
	private List<RfbMember> members;
	
	@Data
	@XmlRootElement(name="arrayitem")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class RfbMember {
		
		@XmlAttribute
		private String iso3;
		
		@XmlPath("name/@en")
		private String englishName;
		
	}
	
}
