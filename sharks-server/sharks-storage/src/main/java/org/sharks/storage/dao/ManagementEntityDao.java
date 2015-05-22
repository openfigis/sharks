package org.sharks.storage.dao;

import java.util.List;

import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;

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
	
	/**
	 * Retrieves all the {@link MgmtEntity} of type Country with almost one {@link PoA} and/or one {@link InformationSource} of type Others.
	 * @param onlyWithPoAsOrOthers <code>true</code> to filter the list, <code>false</code> otherwise.
	 * @return the list of countries.
	 */
	public List<MgmtEntity> listCountries(boolean onlyWithPoAsOrOthers);
	
	/**
	 * Retrieves all the {@link MgmtEntity} of type RFMO with almost one {@link Measure} and/or one {@link InformationSource} of type Others.
	 * @param onlyWithPoAsOrOthers <code>true</code> to filter the list, <code>false</code> otherwise.
	 * @return the list of rfmos.
	 */
	public List<MgmtEntity> listRFMOs(boolean onlyWithMeasuresOrOthers);

}