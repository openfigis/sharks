/**
 * 
 */
package org.sharks.service.moniker.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="FIGISDoc",namespace="http://www.fao.org/fi/figis/devcon/")
@XmlAccessorType(XmlAccessType.NONE)
public class FigisDoc {
	
	@XmlPath("fi:Org/fi:OrgIdent/fi:FigisID/text()")
	private String figisId;
	
	@XmlPath("fi:Org/fi:OrgIdent/fi:OrgRef/fi:ForeignID/@Code")
	private String acronym;
	
	@XmlPath("fi:Org/fi:OrgIdent/fi:OrgRef/fi:ContactEntry/fi:Website/text()")
	private String website;
	
	@XmlPath("fi:Org/fi:OrgIdent/fi:Image/@IID")
	private String imageId;
}
