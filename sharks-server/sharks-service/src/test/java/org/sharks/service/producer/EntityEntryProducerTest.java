/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.EntityEntry;
import org.sharks.storage.dao.InformationSourceDao;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class EntityEntryProducerTest {
	
	@Inject
	EntityEntryProducer producer;
	
	@Produces
	protected InformationSourceDao setupInformationSourceDao() {
		InformationSourceDao dao = Mockito.mock(InformationSourceDao.class);
		when(dao.existsRelatedToEntityByAcronym("ICCAT")).thenReturn(true);
		when(dao.existsRelatedToEntityByAcronym("SEAFO")).thenReturn(false);
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
