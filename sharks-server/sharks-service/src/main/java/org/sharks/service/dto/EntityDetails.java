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
public class EntityDetails {
	
	private final long id;
	private final String acronym;
	private final String name;
	private final Long type;
	private final String logoUrl;
	private final String webSite;
	private final String factsheetUrl;
	private final List<EntityMember> members;
	private final List<MeasureEntry> measures;
	private final List<EntityDocument> others;
	

}
