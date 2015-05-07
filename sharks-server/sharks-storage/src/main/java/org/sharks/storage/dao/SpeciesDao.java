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
import javax.persistence.criteria.Subquery;

import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SpeciesDao extends AbstractDao<Species, Long> {

	@Inject
	public SpeciesDao(EntityManagerFactory emf) {
		super(emf, Species.class);
	}
	
	public List<Species> listWithMeasures() {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Species> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<Species> species = criteriaQuery.from(type);
		
        Subquery<Measure> measureCriteriaQuery = criteriaQuery.subquery(Measure.class);
        Root<Measure> measure = measureCriteriaQuery.from(Measure.class);
        measureCriteriaQuery.select(measure);
		Join<Measure, Species> measuresSpeciesJoin = measure.join("species");
		measureCriteriaQuery.where(criteriaBuilder.equal(measuresSpeciesJoin.get("code"), species.get("code")));
		
		criteriaQuery.where(criteriaBuilder.exists(measureCriteriaQuery));
		
		TypedQuery<Species> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	public Species getByAlphaCode(String alphaCode) {
		return getByField("alphaCode", alphaCode);
	}

}
