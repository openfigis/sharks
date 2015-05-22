/**
 * 
 */
package org.sharks.storage.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	@Override
	public MgmtEntity getByAcronym(String acronym) {
		return getByField("acronym", acronym);
	}

	@Override
	public List<MgmtEntity> list(long type) {
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MgmtEntity> criteriaQuery = criteriaBuilder.createQuery(MgmtEntity.class);
        Root<MgmtEntity> rootEntry = criteriaQuery.from(MgmtEntity.class);
        CriteriaQuery<MgmtEntity> all = criteriaQuery.select(rootEntry);
        all.where(criteriaBuilder.equal(rootEntry.get("mgmtEntityType"), type));
		TypedQuery<MgmtEntity> query = entityManager.createQuery(all);
		return query.getResultList();
	}

}
