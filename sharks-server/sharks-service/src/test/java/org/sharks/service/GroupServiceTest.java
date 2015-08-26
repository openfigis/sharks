/**
 * 
 */
package org.sharks.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;
import static org.sharks.service.test.util.TestModelUtils.aRefPubSpecies;
import static org.sharks.service.test.util.TestModelUtils.aSpecies;
import static org.sharks.service.test.util.TestModelUtils.buildCustomSpeciesGrp;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;
import org.sharks.service.impl.GroupServiceImpl;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.storage.dao.CustomSpeciesGroupDao;
import org.sharks.storage.domain.CustomSpeciesGrp;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({GroupServiceImpl.class})
public class GroupServiceTest {
	
	@Inject
	GroupService service;
	
	@Produces
	public CustomSpeciesGroupDao setupCustomSpeciesGroupDao() {
		CustomSpeciesGroupDao dao = Mockito.mock(CustomSpeciesGroupDao.class);
		
		CustomSpeciesGrp group = buildCustomSpeciesGrp(0,"Deep sharks", aSpecies());
		when(dao.get(0l)).thenReturn(group);
		when(dao.get(-1l)).thenReturn(null);
		
		when(dao.list()).thenReturn(Arrays.asList(group));
		
		return dao;
	}
	
	@Produces
	protected RefPubService setupRefPubService() {
		RefPubService service = Mockito.mock(RefPubService.class);
		RefPubSpecies aRefPubSpecies = aRefPubSpecies();
		when(service.getSpecies("ALV")).thenReturn(aRefPubSpecies);
		return service;
	}

	/**
	 * Test method for {@link org.sharks.service.impl.GroupServiceImpl#get(long)}.
	 */
	@Test
	public void testGet() {
		GroupDetails details = service.get(0l);
		assertNotNull(details);
		assertEquals(new Long(0), details.getCode());
		
		assertEquals(1, details.getSpecies().size());
	}
	
	@Test
	public void testGetMissingGroup() {
		GroupDetails details = service.get(-1l);
		assertNull(details);
	}

	/**
	 * Test method for {@link org.sharks.service.impl.GroupServiceImpl#list()}.
	 */
	@Test
	public void testList() {
		List<GroupEntry> entries = service.list();
		assertNotNull(entries);
		assertFalse(entries.isEmpty());
	}

}
