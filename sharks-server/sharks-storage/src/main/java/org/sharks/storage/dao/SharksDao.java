/**
 * 
 */
package org.sharks.storage.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.sharks.storage.dao.model.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SharksDao {
	
	private static String ENV_PROPERTY = "SHARKS_DB";
	private static String DEV_DB_LOCATION = "../sharks-db/Sharks.accdb";

	private EntityManagerFactory emf;
	
	public SharksDao() {
		
		try {
			
			String dbLocation = getDbLocation();
		
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.url", "jdbc:ucanaccess://"+dbLocation);
			
			/* Create EntityManagerFactory */
			emf = Persistence.createEntityManagerFactory("sharks-storage", properties);
			
		} catch (Exception e) {
			throw new RuntimeException("Access db connection failed", e);
		}
	}
	
	private String getDbLocation() {
		String dbLocation = System.getenv(ENV_PROPERTY);
		if (dbLocation == null) dbLocation = DEV_DB_LOCATION;
		return dbLocation;
	}
	
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
