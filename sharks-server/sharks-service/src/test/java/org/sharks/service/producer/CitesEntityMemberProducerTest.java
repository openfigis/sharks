/**
 * 
 */
package org.sharks.service.producer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.sharks.service.util.TestModelUtils.*;

import java.util.Arrays;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.cites.dto.CitesCountry;
import org.sharks.service.dto.EntityMember;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.dao.ManagementEntityDao;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
public class CitesEntityMemberProducerTest {
	
	@Inject
	CitesEntityMemberProducer producer;
	
	RefPubCountry aCountry;
	
	@Produces
	protected RefPubService setupRefPubService() {
		RefPubService service = mock(RefPubService.class);
		aCountry = aRefPubCountry();
		when(service.getCountryByIso2("AF")).thenReturn(aCountry);
		return service;
	}
	
	@Produces
	protected ManagementEntityDao setupManagementEntityDao() {
		ManagementEntityDao dao = mock(ManagementEntityDao.class);
		MgmtEntity entity = buildEntity(0, "AFG");
		entity.setPoAs(Arrays.asList(buildPoA()));
		when(dao.getByAcronym("AFG")).thenReturn(entity);
		return dao;
	}

	/**
	 * Checks if the data is substituted with the one from the ref pub service.
	 */
	@Test
	public void testProduce() {
		CitesCountry country = createCitesCountry("AFG", "AF", "Afghanistan");
		
		EntityMember member = producer.produce(country);
		assertNotNull(member);
		assertEquals("AFG", member.getCode());
		assertTrue(member.isHasPoAs());
	}
	
	/**
	 * Checks if the data is not changed if is not present in the ref pub service.
	 */
	@Test
	public void testProduceWithMissingIso2() {
		CitesCountry country = createCitesCountry(null, "AF", "Afghanistan");
		
		EntityMember member = producer.produce(country);
		assertNotNull(member);
		assertEquals("AFG", member.getCode());
		assertTrue(member.isHasPoAs());
	}

}
