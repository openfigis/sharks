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
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> rootEntry = cq.from(type);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
	}

	public T get(String code) {
		EntityManager em = emf.createEntityManager();
		return em.find(type, code);
	}

}
