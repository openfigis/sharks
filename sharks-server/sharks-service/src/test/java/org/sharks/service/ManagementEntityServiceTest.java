/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.sharks.service.util.TestModelUtils.buildEntity;
import static org.sharks.service.util.TestModelUtils.buildInformationSource;
import static org.sharks.service.util.TestModelUtils.buildMeasure;
import static org.sharks.service.util.TestModelUtils.createFigisDoc;
import static org.sharks.service.util.TestModelUtils.createMember;

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
import org.sharks.service.moniker.MonikerService;
import org.sharks.storage.dao.InformationSourceDao;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

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
	private ManagementEntityDao setupManagementEntityDao() {
		ManagementEntityDao dao = Mockito.mock(ManagementEntityDao.class);
		
		MgmtEntity entity = buildEntity(0, "ICCAT", buildInformationSource(InformationSourceDao.OTHER_TYPE));
		entity.setMeasures(Arrays.asList(buildMeasure(0, "sym")));
		when(dao.getByAcronym("ICCAT")).thenReturn(entity);
		
		entity = buildEntity(0, "NOT_IN_RFB_MONIKER");
		entity.setMeasures(Arrays.asList(buildMeasure(0, "sym")));
		when(dao.getByAcronym("NOT_IN_RFB_MONIKER")).thenReturn(entity);
		
		when(dao.getByAcronym("NOT_EXISTS")).thenReturn(null);
		
		when(dao.listRFMOs(true)).thenReturn(Arrays.asList(buildEntity(0, "ICCAT")));
		when(dao.listRFMOs(false)).thenReturn(Arrays.asList(buildEntity(0, "ICCAT"), buildEntity(1, "SOFAP")));
		
		return dao;
	}
	
	@Produces
	private MonikerService setupMonikerService() {
		MonikerService service = Mockito.mock(MonikerService.class);
		
		when(service.getFigisDocByAcronym("ICCAT")).thenReturn(createFigisDoc("ICCAT", "1234", "4321", "www.ic.cat", createMember("Italy","IT")));
		when(service.getFigisDocByAcronym("NOT_IN_RFB_MONIKER")).thenReturn(null);
		
		return service;
	}

	/**
	 * Test method for {@link org.sharks.service.impl.ManagementEntityServiceImpl#get(java.lang.String)}.
	 */
	@Test
	public void testGet() {
		EntityDetails details = service.get("ICCAT");
		assertNotNull(details);
		assertNotNull(details.getWebSite());
		assertNotNull(details.getMembers());
		assertFalse(details.getMembers().isEmpty());
		assertFalse(details.getOthers().isEmpty());
	}
	
	@Test
	public void testGetWrongEntity() {
		EntityDetails details = service.get("NOT_EXISTS");
		assertNull(details);
	}
	
	
	@Test
	public void testGetMissingRfbMonikerEntry() {
		EntityDetails details = service.get("NOT_IN_RFB_MONIKER");
		assertNotNull(details);
		assertNull(details.getWebSite());
		assertNotNull(details.getMembers());
		assertTrue(details.getMembers().isEmpty());
		
	}

	/**
	 * Test method for {@link org.sharks.service.impl.ManagementEntityServiceImpl#list()}.
	 */
	@Test
	public void testList() {
		List<EntityEntry> entities = service.list(false);
		assertNotNull(entities);
		assertEquals(2,entities.size());
	}
	
	@Test
	public void testListOnlyWithMeasuresOrOthers() {
		List<EntityEntry> entities = service.list(true);
		assertEquals(1,entities.size());
	}

}
