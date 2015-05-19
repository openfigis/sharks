package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.InformationSource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface InformationSourceDao {

	public List<InformationSource> listRelatedToEntity(Long entityCode, Long... types);
	
	public Boolean existsRelatedToEntityByAcronym(String acronym);

}