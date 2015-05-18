package org.sharks.storage.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.InformationSource;

@RunWith(CdiRunner.class)
@AdditionalClasses({TestProducers.class, InformationSourceDaoImpl.class})
public class InformationSourceDaoTest {

	@Inject
	InformationSourceDao dao;

	@Test
	public void testListRelatedToEntity() {
		List<InformationSource> sources = dao.listRelatedToEntity(1l);
		
		assertNotNull(sources);
		assertEquals(2, sources.size());
	}
	
	@Test
	public void testListRelatedToEntityWithTypes() {
		List<InformationSource> sources = dao.listRelatedToEntity(1l, 0l);
		
		assertNotNull(sources);
		assertTrue(sources.isEmpty());
	}
	
	
}
