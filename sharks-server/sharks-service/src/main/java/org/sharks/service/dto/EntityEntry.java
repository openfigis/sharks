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
public class EntityEntry {
	
	private final long id;
	private final String acronym;
	private final String name;

}
