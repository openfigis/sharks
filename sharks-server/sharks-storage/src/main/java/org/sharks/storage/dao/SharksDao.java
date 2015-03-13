/**
 * 
 */
package org.sharks.storage.dao;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.sharks.storage.domain.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class SharksDao {
	
	@Inject
	private EntityManagerFactory emf;
	
	public List<Species> listAllSpecies() {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Species> cq = cb.createQuery(Species.class);
        Root<Species> rootEntry = cq.from(Species.class);
        CriteriaQuery<Species> all = cq.select(rootEntry);
        TypedQuery<Species> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}

	public Species getSpecies(String code) {
		EntityManager em = emf.createEntityManager();
		return em.find(Species.class, code);
	}

}
