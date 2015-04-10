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
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasureDao extends AbstractDao<Measure, Long> {

	@Inject
	public MeasureDao(EntityManagerFactory emf) {
		super(emf, Measure.class);
	}
	
	/**
	 * Retrieves all the {@link Measure} related to the specified {@link Species}.
	 * @param alphaCode the {@link Species} alpha code.
	 * @return the found {@link Measure}s.
	 */
	public List<Measure> allRelatedToSpeciesAlphaCode(String alphaCode) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Measure> cq = cb.createQuery(type);
		Root<Measure> measure = cq.from(type);
		Join<Measure, Species> species = measure.join("species");
		cq.where(cb.equal(species.get("alphaCode"), alphaCode));
		
		CriteriaQuery<Measure> all = cq.select(measure);
		TypedQuery<Measure> allQuery = em.createQuery(all);
		return allQuery.getResultList();
		
	}
	
	public List<Measure> allRelatedToManagementEntityAcronym(String acronym) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Measure> cq = cb.createQuery(type);
		Root<Measure> measure = cq.from(type);
		Join<Measure, MgmtEntity> entities = measure.join("mgmtEntity");
		cq.where(cb.equal(entities.get("acronym"), acronym));
		
		CriteriaQuery<Measure> all = cq.select(measure);
		TypedQuery<Measure> allQuery = em.createQuery(all);
		return allQuery.getResultList();
		
	}

}
