package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MeasureDao {

	/**
	 * Retrieves all the {@link Measure} related to the specified {@link MgmtEntity} acronym.
	 * @param acronym the {@link MgmtEntity} acronym.
	 * @return the list of {@link Measure} found, or an empty list if the acronym does not exist.
	 */
	public List<Measure> listRelatedToManagementEntityAcronym(String acronym);

}