/**
 * 
 */
package org.sharks.storage.dao;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;

import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityDao extends AbstractDao<MgmtEntity, String> {

	@Inject
	public ManagementEntityDao(EntityManagerFactory emf) {
		super(emf, MgmtEntity.class);
	}
	
	/**
	 * Gets a {@link MgmtEntity} by his acronym.
	 * @param acronym the acronym.
	 * @return the found {@link MgmtEntity} or <code>null</code> if not found.
	 */
	public MgmtEntity getByAcronym(String acronym) {
		return getByField("acronym", acronym);
	}

}
