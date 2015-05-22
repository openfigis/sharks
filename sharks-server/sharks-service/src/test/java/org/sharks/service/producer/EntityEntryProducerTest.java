/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.*;

import java.util.Collections;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.InformationSource;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class EntityEntryProducerTest {
	
	@Inject
	EntityEntryProducer producer;
	
	@Produces
	protected ManagementEntityDao setupInformationSourceDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		MgmtEntity entity = buildEntity(0, "ICCAT");
		entity.setInformationSources(Collections.singletonList(new InformationSource()));
		when(dao.getByAcronym("ICCAT")).thenReturn(entity);
		when(dao.getByAcronym("SEAFO")).thenReturn(buildEntity(0, "SEAFO"));
		return dao;
	}

	/**
	 * Checks if the {@link EntityEntry} is produced using the code from the dao.
	 */
	@Test
	public void testProduce() {
		EntityEntry entry = producer.produce("ICCAT");
		assertNotNull(entry);
		assertEquals("ICCAT", entry.getAcronym());
		assertTrue(entry.isHasInformationSources());
	}
	
	/**
	 * Checks if the id is not set when the entity is not in the dao
	 */
	@Test
	public void testProduceUsingEntityWithoutInformationSources() {
		EntityEntry entry = producer.produce("SEAFO");
		assertNotNull(entry);
		assertEquals("SEAFO", entry.getAcronym());
		assertFalse(entry.isHasInformationSources());
	}

}
