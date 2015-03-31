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
@XmlRootElement(name="conceptList",namespace="http://www.fao.org/fi/refpub/webservice")
@XmlAccessorType(XmlAccessType.NONE)
public class RefPubCountries {
	
	@XmlElement(name="concept")
	private List<RefPubCountry> countries;
	
	@XmlElement(name="link")
	private List<Link> links;
	
	@XmlAttribute(name="total_record")
	private int total;
	
	public Link getNextLink() {
		return findLink("next");
	}
	
	private Link findLink(String ref) {
		return links.stream().filter(link->link.getRel().equals(ref)).findFirst().orElse(null);
	}
	
	@Data
	@XmlRootElement(name="link",namespace="http://www.fao.org/fi/refpub/webservice")
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Link {
		
		@XmlAttribute
		private String rel;
		
		@XmlAttribute
		private String href;
	}

}
