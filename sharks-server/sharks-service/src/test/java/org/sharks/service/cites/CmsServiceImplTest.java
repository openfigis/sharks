package org.sharks.service.cites;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sharks.service.cms.CmsServiceImpl;

@RunWith(CdiRunner.class)
public class CmsServiceImplTest extends Base {

	String iso3 = "deu";

	@Inject
	CmsServiceImpl cmsService;

	@Test
	public void getMember() {
		assertTrue(cmsService.getMember(iso3).get().getIso3().equals(iso3));
		assertFalse(cmsService.getMember("").isPresent());
	}

	@Test
	public void getParties() {
		assertTrue(cmsService.getParties().size() > 115);
	}

}