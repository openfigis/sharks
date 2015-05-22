/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement
public class CountryDetails {

	private final String code;
	private final String name;
	private final List<EntityEntry> rfbs;
	private final List<PoAEntry> poas;
	private final List<EntityDocument> others;
}
