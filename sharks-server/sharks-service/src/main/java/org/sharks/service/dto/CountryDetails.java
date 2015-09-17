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
	private final List<CountryEntity> rfbs;
	private final List<EntityDocument> memberships;
	private final List<PoAEntry> poas;
	private final List<EntityDocument> others;
	private final List<FaoLexDocument> faoLexDocuments;
}
