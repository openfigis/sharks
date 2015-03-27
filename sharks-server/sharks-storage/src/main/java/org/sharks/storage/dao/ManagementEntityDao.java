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
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class ManagementEntityDao extends AbstractDao<MgmtEntity> {

	@Inject
	public ManagementEntityDao(EntityManagerFactory emf) {
		super(emf, MgmtEntity.class);
	}
	
	public MgmtEntity getByAcronym(String acronym) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MgmtEntity> cq = cb.createQuery(type);
        Root<MgmtEntity> rootEntry = cq.from(type);
        CriteriaQuery<MgmtEntity> all = cq.select(rootEntry);
        cq.where(cb.equal(rootEntry.get("acronym"), acronym));
        
        TypedQuery<MgmtEntity> allQuery = em.createQuery(all);
        return allQuery.getSingleResult();
	}
	
	public List<MgmtEntity> allRelatedToCountry(String countryCode) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<MgmtEntity> cq = cb.createQuery(type);
        Root<MgmtEntity> rootEntry = cq.from(type);
        CriteriaQuery<MgmtEntity> all = cq.select(rootEntry);
        
        Join<Country, MgmtEntity> countries = rootEntry.join("countries");
		cq.where(cb.equal(countries.get("code"), countryCode));
        
        TypedQuery<MgmtEntity> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}

}
