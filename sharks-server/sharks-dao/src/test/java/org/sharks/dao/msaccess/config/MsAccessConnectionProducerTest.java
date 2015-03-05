package org.sharks.dao.msaccess.config;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.dao.msaccess.config.MsAccessConnectionProducer;
import org.sharks.dao.msaccess.config.SharksConnection;

@RunWith(CdiRunner.class)
@AdditionalClasses(MsAccessConnectionProducer.class)
public class MsAccessConnectionProducerTest {

	@Inject
	private SharksConnection sharksConnection;

	@Test
	public void testGetConnecton() throws ClassNotFoundException {

		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		assertNotNull(sharksConnection);
		assertNotNull(sharksConnection.getConnection());
	}

}
