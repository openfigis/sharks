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

import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasuresDao extends AbstractDao<Measure> {

	@Inject
	public MeasuresDao(EntityManagerFactory emf) {
		super(emf, Measure.class);
	}
	
	public List<Measure> allRelatedToSpecies(String code) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Measure> cq = cb.createQuery(type);
		Root<Measure> measure = cq.from(type);
		Join<Measure, Species> species = measure.join("species");
		cq.where(cb.equal(species.get("code"), code));
		
		CriteriaQuery<Measure> all = cq.select(measure);
		TypedQuery<Measure> allQuery = em.createQuery(all);
		return allQuery.getResultList();
		
	}

}
