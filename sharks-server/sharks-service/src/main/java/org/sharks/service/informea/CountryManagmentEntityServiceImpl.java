package org.sharks.service.informea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.cites.CitesServiceImpl;
import org.sharks.service.cms.CmsServiceImpl;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.informea.dto.InformeaCountry;

/**
 * 
 * @author Erik van Ingen
 *
 */
public class CountryManagmentEntityServiceImpl implements CountryManagmentEntityService {

	@Inject
	CitesServiceImpl citesService;

	@Inject
	ManagementEntityService managementEntityService;

	@Inject
	CmsServiceImpl cmsService;

	@Override
	public List<EntityDetails> getMesForCountry(String iso3) {
		List<EntityDetails> found = new ArrayList<EntityDetails>();

		Optional<InformeaCountry> cites = cmsService.getMember(iso3.toLowerCase());
		if (cites.isPresent()) {
			EntityDetails ed = managementEntityService.get("CMS");
			found.add(ed);
		}
		Optional<InformeaCountry> cms = citesService.getMember(iso3.toUpperCase());
		if (cms.isPresent()) {
			EntityDetails ed = managementEntityService.get("CITES");
			found.add(ed);
		}
		return found;
	}

}
