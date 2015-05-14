/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.builEntity;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class EntityEntryProducerTest {
	
	@Inject
	EntityEntryProducer producer;
	
	MgmtEntity anEntity;
	
	@Produces
	protected ManagementEntityDao setupManagementEntityDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		anEntity = builEntity(1,"ICCAT");
		when(dao.getByAcronym("ICCAT")).thenReturn(anEntity);
		return dao;
	}

	/**
	 * Checks if the {@link EntityEntry} is produced using the code from the dao.
	 */
	@Test
	public void testProduceUsingDao() {
		EntityEntry entry = producer.produce("ICCAT");
		assertNotNull(entry);
		assertEquals("ICCAT", entry.getAcronym());
		
		//the id has been set using the code from dao item
		assertNotNull(entry.getId());
		assertEquals(anEntity.getCode(), entry.getId());
	}
	
	/**
	 * Checks if the id is not set when the entity is not in the dao
	 */
	@Test
	public void testProduceWithouthDao() {
		EntityEntry entry = producer.produce("NOT_EXISTS");
		assertNotNull(entry);
		assertEquals("NOT_EXISTS", entry.getAcronym());
		
		assertNull(entry.getId());
	}

}
