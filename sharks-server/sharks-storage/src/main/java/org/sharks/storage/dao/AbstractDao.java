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
public abstract class AbstractDao<T, I> {
	
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

	public T get(I code) {
		EntityManager em = emf.createEntityManager();
		return em.find(type, code);
	}
	
	
	protected T getByField(String fieldName, String value) {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> rootEntry = cq.from(type);
        CriteriaQuery<T> all = cq.select(rootEntry);
        cq.where(cb.equal(rootEntry.get(fieldName), value));
        
        TypedQuery<T> allQuery = em.createQuery(all);
        List<T> result = allQuery.getResultList();
        return result.isEmpty()?null:result.get(0);
	}

}
