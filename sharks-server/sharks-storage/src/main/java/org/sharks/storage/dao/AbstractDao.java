/**
 * 
 */
package org.sharks.storage.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public abstract class AbstractDao<T> {
	
	protected EntityManagerFactory emf;
	
	protected Class<T> type;
	
	public AbstractDao(EntityManagerFactory emf, Class<T> type) {
		this.emf = emf;
		this.type = type;
	}
	
	public List<T> list() {
		
		EntityManager entityManager = emf.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(type);
        Root<T> rootEntry = criteriaQuery.from(type);
        CriteriaQuery<T> all = criteriaQuery.select(rootEntry);
		TypedQuery<T> query = entityManager.createQuery(all);
		return query.getResultList();
	}

	public T get(String code) {
		EntityManager em = emf.createEntityManager();
		return em.find(type, code);
	}

}
