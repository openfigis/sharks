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

	/**
	 * FIXME should we remove this method?
	 */
	public List<MgmtEntity> list(long type);
	
	/**
	 * Retrieves all the {@link MgmtEntity} of type Country.
	 * @param onlyWithPoAs <code>true</code> to select only the countries with {@link PoA} associated.
	 * @param onlyWithOthersSources <code>true</code> to select only the countries with {@link InformationSource} of type Other associated.
	 * @return the list of countries.
	 */
	public List<MgmtEntity> listCountries(boolean onlyWithPoAs, boolean onlyWithOthersSources);
	
	/**
	 * Retrieves all the {@link MgmtEntity} of type RFMO and Institution.
	 * @param onlyWithMeasures <code>true</code> to selected only entities with {@link Measure} associated.
	 * @param onlyWithOthersSources <code>true</code> to select only the entities with {@link InformationSource} of type Other associated.
	 * @return the list of entities.
	 */
	public List<MgmtEntity> listRFMOsAndInstitutions(boolean onlyWithMeasures, boolean onlyWithOthersSources);

}