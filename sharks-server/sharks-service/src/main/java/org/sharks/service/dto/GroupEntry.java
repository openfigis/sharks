/**
 * 
 */
package org.sharks.service.dto;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class GroupEntry {
	
	private final Long code;
	private final String name;

}
