/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.buildEntity;

import java.util.Collections;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.CountryEntity;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.Measure;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class CountryEntityProducerTest {
	
	@Inject
	CountryEntityProducer producer;
	
	@Produces
	protected ManagementEntityDao setupInformationSourceDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		MgmtEntity entity = buildEntity(0, "ICCAT");
		entity.setMeasures(Collections.singletonList(new Measure()));
		when(dao.getByAcronym("ICCAT")).thenReturn(entity);
		when(dao.getByAcronym("SEAFO")).thenReturn(buildEntity(0, "SEAFO"));
		return dao;
	}

	/**
	 * Checks if the {@link EntityEntry} is produced using the code from the dao.
	 */
	@Test
	public void testProduce() {
		CountryEntity entity = producer.produce("ICCAT");
		assertNotNull(entity);
		assertEquals("ICCAT", entity.getAcronym());
		assertTrue(entity.isHasMeasures());
	}
	
	/**
	 * Checks if the id is not set when the entity is not in the dao
	 */
	@Test
	public void testProduceUsingEntityWithoutInformationSources() {
		CountryEntity entity = producer.produce("SEAFO");
		assertNotNull(entity);
		assertEquals("SEAFO", entity.getAcronym());
		assertFalse(entity.isHasMeasures());
	}

}
