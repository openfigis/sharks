/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.builEntity;
import static org.sharks.service.util.TestModelUtils.buildMeasure;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.impl.ManagementEntityServiceImpl;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.dao.MeasureDao;
import org.sharks.storage.dao.MeasureDaoImpl;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({ManagementEntityServiceImpl.class})
public class ManagementEntityServiceTest {
	
	@Inject
	ManagementEntityService service;
	
	@Produces
	private MeasureDao setupMeasureDao() {
		MeasureDao dao = Mockito.mock(MeasureDaoImpl.class);
		
		when(dao.listRelatedToManagementEntityAcronym("ICCAT")).thenReturn(Arrays.asList(buildMeasure(0, "sym")));
		
		return dao;
	}
	
	@Produces
	private ManagementEntityDao setupManagementEntityDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		
		when(dao.getByAcronym("ICCAT")).thenReturn(builEntity(0, "ICCAT"));
		when(dao.getByAcronym("NOT_EXISTS")).thenReturn(null);
		
		when(dao.list()).thenReturn(Arrays.asList(builEntity(0, "ICCAT")));
		
		return dao;
	}

	/**
	 * Test method for {@link org.sharks.service.impl.ManagementEntityServiceImpl#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		EntityDetails details = service.get("ICCAT");
		assertNotNull(details);
	}
	
	@Test
	public void testGetWrongEntity() {
		EntityDetails details = service.get("NOT_EXISTS");
		assertNull(details);
	}

	/**
	 * Test method for {@link org.sharks.service.impl.ManagementEntityServiceImpl#list()}.
	 */
	@Test
	public void testList() {
		List<EntityEntry> entities = service.list();
		assertNotNull(entities);
		assertFalse(entities.isEmpty());
	}

}
