package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.InformationSource;

public interface InformationSourceDao {

	public List<InformationSource> listRelatedToEntity(Long entityCode, Long... types);

}