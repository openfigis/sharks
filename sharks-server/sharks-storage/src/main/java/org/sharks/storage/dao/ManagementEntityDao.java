package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface ManagementEntityDao {
	
	static long INSTITUTION_TYPE = 1l;
	static long RFMO_TYPE = 2l;
	static long COUNTRY_TYPE = 3l;

	/**
	 * Gets a {@link MgmtEntity} by his acronym.
	 * @param acronym the acronym.
	 * @return the found {@link MgmtEntity} or <code>null</code> if not found.
	 */
	public MgmtEntity getByAcronym(String acronym);

	public List<MgmtEntity> list(long type);

}