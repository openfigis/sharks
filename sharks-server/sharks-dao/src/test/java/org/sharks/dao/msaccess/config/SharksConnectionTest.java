package org.sharks.dao.msaccess.config;

import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
@AdditionalClasses(MsAccessConnectionProducer.class)
public class SharksConnectionTest {

	@Inject
	SharksConnection sc;

	@Test
	public void testGetConnection() {

		sc.getConnection();
		try {
			sc.getConnection();
			fail();
		} catch (Exception e) {
		}
		sc.release();
		try {
			sc.release();
			fail();
		} catch (Exception e) {
		}
	}

}
