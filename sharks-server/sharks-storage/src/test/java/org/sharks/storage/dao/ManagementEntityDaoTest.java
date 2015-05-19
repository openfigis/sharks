package org.sharks.storage.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({TestProducers.class, ManagementEntityDaoImpl.class})
public class ManagementEntityDaoTest {

	@Inject
	ManagementEntityDao dao;
	
	@Test
	public void testGetByAcronym() {
		MgmtEntity entity = dao.getByAcronym("ICCAT");
		assertNotNull(entity);
		assertEquals("ICCAT", entity.getAcronym());
		assertEquals(new Long(1), entity.getCode());
	}
	
	@Test
	public void testGetByAcronymWithWrongAcronym() {
		MgmtEntity entity = dao.getByAcronym("NOT_EXISTS");
		assertNull(entity);
	}
	
	@Test
	public void testListRelatedToInformationSource() {
		List<MgmtEntity> entities = dao.listRelatedToInformationSource();
		assertNotNull(entities);
		assertEquals(9, entities.size());
	}	

}
