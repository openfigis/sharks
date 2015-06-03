package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
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
	public void testList() {
		
		List<MgmtEntity> institutions = dao.list(ManagementEntityDao.INSTITUTION_TYPE);
		assertEquals(3, institutions.size());
		
		List<MgmtEntity> countries = dao.list(ManagementEntityDao.COUNTRY_TYPE);
		assertEquals(203, countries.size());

		List<MgmtEntity> rfmos = dao.list(ManagementEntityDao.RFMO_TYPE);
		assertEquals(18, rfmos.size());
	}	
	
	@Test
	public void testListCountries() {
		
		List<MgmtEntity> countries = dao.listCountries(false, false);
		assertEquals(203, countries.size());
	}
	
	@Test
	public void testListCountriesOnlyWithPoAs() {
		
		List<MgmtEntity> countries = dao.listCountries(true, false);
		assertEquals(36, countries.size());
	}
	
	@Test
	public void testListCountriesOnlyWithOtherSources() {
		
		List<MgmtEntity> countries = dao.listCountries(false, true);
		assertEquals(0, countries.size());
	}
	
	@Test
	public void testListCountriesOnlyWithPoAsAndOtherSources() {
		
		List<MgmtEntity> countries = dao.listCountries(true, true);
		assertEquals(0, countries.size());
	}
	
	@Test
	public void testListRFMOsAndInstitutions() {
		
		List<MgmtEntity> rfmos = dao.listRFMOsAndInstitutions(false, false);
		assertEquals(21, rfmos.size());
	}
	
	@Test
	public void testListRFMOsAndInstitutionsWithMeasures() {
		
		List<MgmtEntity> rfmos = dao.listRFMOsAndInstitutions(true, false);
		assertEquals(12, rfmos.size());
	}
	
	@Test
	public void testListRFMOsAndInstitutionsWithOtherSources() {
		
		List<MgmtEntity> rfmos = dao.listRFMOsAndInstitutions(false, true);
		assertEquals(21, rfmos.size());
	}
	
	@Test
	public void testListRFMOsAndInstitutionsWithMeasuresAndOtherSources() {
		
		List<MgmtEntity> rfmos = dao.listRFMOsAndInstitutions(true, true);
		assertEquals(12, rfmos.size());
	}

}
