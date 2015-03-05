package org.sharks.dao.msaccess.tools;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.ucanaccess.jdbc.UcanaccessDriver;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(CdiRunner.class)
public class MsAccessConnectionProviderTest {

	@Inject
	private MsAccessConnectionProducer p;

	@Test
	public void testGetConnecton() throws ClassNotFoundException {

		UcanaccessDriver l;

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		assertNotNull(p.getConnection());
	}

}
