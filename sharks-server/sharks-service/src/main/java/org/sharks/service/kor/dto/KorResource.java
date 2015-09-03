/**
 * 
 */
package org.sharks.service.kor.dto;

import java.io.Serializable;

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
@XmlRootElement(name="resources")
@XmlAccessorType(XmlAccessType.NONE)
public class KorResource implements Serializable {

	private static final long serialVersionUID = -8493809632407167558L;

	@XmlPath("link/dc:identifier[@scheme='ICON URI']/text()")
	private String iconUrl;
	
	@XmlPath("link/dc:identifier[@scheme='URI']/text()")
	private String uri;

}
