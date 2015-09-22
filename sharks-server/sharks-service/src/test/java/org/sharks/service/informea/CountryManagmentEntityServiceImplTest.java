package org.sharks.service.informea;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.config.ConfigurationProducer;
import org.sharks.service.ManagementEntityService;
import org.sharks.service.cites.Base;
import org.sharks.service.http.DefaultHttpClient;

@RunWith(CdiRunner.class)
@AdditionalClasses({ CountryManagmentEntityServiceImpl.class, DefaultHttpClient.class, ConfigurationProducer.class })
public class CountryManagmentEntityServiceImplTest extends Base {

	String iso3 = "DEU";

	@Inject
	CountryManagmentEntityService s;

	@Test
	public void test() {
		assertEquals(2, s.getMesForCountry(iso3).size());
	}

	@Produces
	private ManagementEntityService managementEntityService() {
		return mock(ManagementEntityService.class);
	}

}
