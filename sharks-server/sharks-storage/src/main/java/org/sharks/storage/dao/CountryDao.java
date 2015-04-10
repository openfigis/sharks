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

import org.sharks.storage.domain.Country;
import org.sharks.storage.domain.MgmtEntity;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryDao extends AbstractDao<Country, String> {

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
	
	public List<Country> listWithPoAs() {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<Country> countries = criteriaQuery.from(type);
		
        Subquery<PoA> poasCriteriaQuery = criteriaQuery.subquery(PoA.class);
        Root<PoA> poa = poasCriteriaQuery.from(PoA.class);
        poasCriteriaQuery.select(poa);
		Join<PoA, Country> poasCountriesJoin = poa.join("countries");
		poasCriteriaQuery.where(criteriaBuilder.equal(poasCountriesJoin.get("code"), countries.get("code")));
		
		criteriaQuery.where(criteriaBuilder.exists(poasCriteriaQuery));
		
		TypedQuery<Country> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
