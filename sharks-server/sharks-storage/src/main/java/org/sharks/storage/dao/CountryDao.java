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
public class CountryDao extends AbstractDao<Country> {

	@Inject
	public CountryDao(EntityManagerFactory emf) {
		super(emf, Country.class);
	}
	
	public List<Country> allCountriesForManagementEntityAcronym(String acronym) {
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(type);
		Root<Country> country = cq.from(type);
		Join<Country, MgmtEntity> entities = country.join("mgmtEntity");
		cq.where(cb.equal(entities.get("acronym"), acronym));
		
		CriteriaQuery<Country> all = cq.select(country);
		TypedQuery<Country> allQuery = em.createQuery(all);
		return allQuery.getResultList();
		
	}

}
