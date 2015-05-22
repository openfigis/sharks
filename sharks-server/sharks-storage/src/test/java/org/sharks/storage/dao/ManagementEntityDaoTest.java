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
		assertEquals(204, countries.size());

		List<MgmtEntity> rfmos = dao.list(ManagementEntityDao.RFMO_TYPE);
		assertEquals(18, rfmos.size());
	}	
	
	@Test
	public void testListCountries() {
		
		List<MgmtEntity> countries = dao.listCountries(false);
		assertEquals(204, countries.size());
	}
	
	@Test
	public void testListCountriesOnlyWithPoAsOrOthers() {
		
		List<MgmtEntity> countries = dao.listCountries(true);
		assertEquals(35, countries.size());
	}
	
	@Test
	public void testListRFMOS() {
		
		List<MgmtEntity> rfmos = dao.listRFMOs(false);
		assertEquals(18, rfmos.size());
	}
	
	@Test
	public void testListRFMOSOnlyWithMeasuresOrOthers() {
		
		List<MgmtEntity> rfmos = dao.listRFMOs(true);
		assertEquals(10, rfmos.size());
	}

}
