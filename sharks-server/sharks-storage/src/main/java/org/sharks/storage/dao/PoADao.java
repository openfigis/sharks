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

import org.sharks.storage.domain.PoA;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class PoADao extends AbstractDao<PoA> {

	@Inject
	public PoADao(EntityManagerFactory emf) {
		super(emf, PoA.class);
	}
	
	public List<PoA> allRelatedToCountry(String code) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PoA> cq = cb.createQuery(type);
		Root<PoA> poa = cq.from(type);
		Join<PoA, Species> countries = poa.join("countries");
		cq.where(cb.equal(countries.get("code"), code));
		
		CriteriaQuery<PoA> all = cq.select(poa);
		TypedQuery<PoA> allQuery = em.createQuery(all);
		return allQuery.getResultList();
		
	}

}
