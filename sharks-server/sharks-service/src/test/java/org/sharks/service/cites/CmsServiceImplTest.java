package org.sharks.service.cites;

import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.cms.CmsService;
import org.sharks.service.cms.CmsServiceImpl;

@RunWith(CdiRunner.class)
@AdditionalClasses({ CmsServiceImpl.class })
public class CmsServiceImplTest extends Base {

	String iso3 = "deu";

	@Inject
	CmsService cmsService;

	@Test
	public void isMember() {
		assertTrue(cmsService.isMember(iso3));
	}

	@Test
	public void getParties() {
		assertTrue(cmsService.getParties().size() > 115);
	}

}