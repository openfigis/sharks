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
	
	/**
	 * Retrieves all the {@link Species} with almost a {@link Measure} related.
	 * @return the list of {@link Species}.
	 */
	public List<Species> listWithMeasures() {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Species> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<Species> species = criteriaQuery.from(type);
			
		criteriaQuery.where(criteriaBuilder.isNotEmpty(species.get("measures")));
		
		TypedQuery<Species> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
	
	/**
	 * Returns the {@link Species} with the specified alpha code.
	 * @param alphaCode the species alpha code.
	 * @return the found {@link Species}, <code>null</code> otherwise.
	 */
	public Species getByAlphaCode(String alphaCode) {
		return getByField("alphaCode", alphaCode);
	}

}
