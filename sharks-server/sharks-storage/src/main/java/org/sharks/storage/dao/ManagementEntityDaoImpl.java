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
public class ManagementEntityDaoImpl extends AbstractDao<MgmtEntity, String> implements ManagementEntityDao {

	@Inject
	public ManagementEntityDaoImpl(EntityManagerFactory emf) {
		super(emf, MgmtEntity.class);
	}
	
	/* (non-Javadoc)
	 * @see org.sharks.storage.dao.ManagementEntityDao#getByAcronym(java.lang.String)
	 */
	@Override
	public MgmtEntity getByAcronym(String acronym) {
		return getByField("acronym", acronym);
	}

}
