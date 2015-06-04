/**
 * 
 */
package org.sharks.storage.dao;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 *
 *         http://stackoverflow.com/questions/4543947/when-should-entitymanagerfactory-instance-be-created-opened?rq=1
 *
 *
 */
@Slf4j
@ApplicationScoped
public class Producers {

	@Produces
	@Singleton
	public EntityManagerFactory getEntityManagerFactory(Configuration configuration) {

		try {

			String dbLocation = configuration.getDbFileLocation();
			log.trace("dbLocation {}", dbLocation);

			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.url", "jdbc:ucanaccess://" + dbLocation);

			EntityManagerFactory emf = Persistence.createEntityManagerFactory("sharks-storage", properties);

			return emf;

		} catch (Exception e) {
			throw new SharksStorageException("EntityManagerFactory creation failed", e);
		}
	}

	void closeEntityManagerFactory(@Disposes EntityManagerFactory emf) {
		emf.close();
	}

}
