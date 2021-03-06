/**
 * 
 */
package org.sharks.service.moniker.dto;

import java.io.Serializable;
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
public class Rfb implements Serializable {
	
	private static final long serialVersionUID = -5002136723077810901L;

	@XmlAttribute
	private String fid;
	
	//used only in rfb4iso3 moniker
	@XmlAttribute(name="FigisID")
	private String figisId;
	
	@XmlAttribute
	private String acronym;
	
	@XmlAttribute
	private String website;
	
	@XmlAttribute
	private String logo;
	
	@XmlAttribute
	private String link;
	
	@XmlPath("members/member")
	private List<Member> members;
	
	@Data
	@XmlRootElement(name="member")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Member implements Serializable {
		
		private static final long serialVersionUID = 5978821966908190147L;

		@XmlAttribute
		private String iso3;
		
		@XmlPath("name/@en")
		private String englishName;
	}
	
}
