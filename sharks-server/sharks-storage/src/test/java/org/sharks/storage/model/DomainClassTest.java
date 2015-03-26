/**
 * 
 */
package org.sharks.storage.model;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.sharks.storage.TestConstants;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(Parameterized.class)
public class DomainClassTest {
	
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void emfSetup() throws Exception {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.url", "jdbc:ucanaccess://" + TestConstants.TEST_DB_LOCATION);

		emf = Persistence.createEntityManagerFactory("sharks-storage", properties);
	}
	
	@Parameters(name= "{0}")
	public static Collection<Object[]> getDomainClasses() {
		Reflections reflections = new Reflections(TestConstants.DOMAIN_PACKAGE, new SubTypesScanner(false));
		Collection<Object[]> classes = new ArrayList<Object[]>();
		for (Class<? extends Object> dclass:reflections.getSubTypesOf(Object.class)) classes.add(new Object[]{dclass});
		return classes;
	}

	@AfterClass
	public static void closeEmf() throws SQLException {
		if (emf!=null) emf.close();
	}
	
	private Class<Object> domainClass;
	
	public DomainClassTest(Class<Object> domainClass) {
		this.domainClass = domainClass;
	}
	
	@Test
	public void testListing() {
		EntityManager em = emf.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(domainClass);
		Root<Object> rootEntry = cq.from(domainClass);
		CriteriaQuery<Object> all = cq.select(rootEntry);
		TypedQuery<Object> allQuery = em.createQuery(all);
		List<Object> items = allQuery.getResultList();
		assertNotNull(items);
	}
}
