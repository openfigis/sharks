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

import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryDaoImpl extends AbstractDao<Country, String> implements CountryDao {

	@Inject
	public CountryDaoImpl(EntityManagerFactory emf) {
		super(emf, Country.class);
	}
	
	/* (non-Javadoc)
	 * @see org.sharks.storage.dao.CountryDao#listWithPoAs()
	 */
	@Override
	public List<Country> listWithPoAs() {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(type);
		Root<Country> countries = criteriaQuery.from(type);
		
		criteriaQuery.where(criteriaBuilder.isNotEmpty(countries.get("poAs")));
		
		TypedQuery<Country> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}

}
