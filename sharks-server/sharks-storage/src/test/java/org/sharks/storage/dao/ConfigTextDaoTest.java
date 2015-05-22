package org.sharks.storage.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.storage.TestProducers;
import org.sharks.storage.domain.ConfigText;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@RunWith(CdiRunner.class)
@AdditionalClasses(TestProducers.class)
public class ConfigTextDaoTest {

	@Inject
	ConfigTextDao dao;

	@Test
	public void testGetByKeyword() {
		ConfigText text = dao.getByKeyword("TITLE");
		assertNotNull(text);
		assertEquals("TITLE", text.getCdMnemonicKey());
		assertEquals(new Long(1), text.getCode());		
	}
	
	@Test
	public void testGetByKeywordWithWrongKeyword() {
		ConfigText text = dao.getByKeyword("NOT_EXISTS");
		assertNull(text);

	}
}
