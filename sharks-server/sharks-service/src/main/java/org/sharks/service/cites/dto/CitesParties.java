/**
 * 
 */
package org.sharks.service.cites.dto;

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
public class CitesParties {

	@XmlPath("Treaty/Participants/Table/Tbody/Rows/Row")
	private List<CitesCountry> countries;

}
