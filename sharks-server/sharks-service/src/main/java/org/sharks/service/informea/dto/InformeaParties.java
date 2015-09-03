/**
 * 
 */
package org.sharks.service.informea.dto;

import java.io.Serializable;
import java.util.List;

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
@XmlRootElement(name="Document")
@XmlAccessorType(XmlAccessType.NONE)
public class InformeaParties implements Serializable {

	private static final long serialVersionUID = -1226814243690739548L;
	
	@XmlPath("Treaty/Participants/Table/Tbody/Rows/Row")
	private List<InformeaCountry> countries;

}
