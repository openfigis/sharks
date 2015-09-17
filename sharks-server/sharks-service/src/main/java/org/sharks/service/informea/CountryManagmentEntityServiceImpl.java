package org.sharks.service.informea;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import org.sharks.service.cites.CitesServiceImpl;
import org.sharks.service.cms.CmsServiceImpl;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.external.ExternalService;
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
	CmsServiceImpl cmsService;

	@Override
	public List<EntityEntry> getMesForCountry(String iso3) {
		List<EntityEntry> found = new ArrayList<EntityEntry>();
		add(iso3.toLowerCase(), cmsService, found);
		add(iso3.toUpperCase(), citesService, found);
		return found;
	}

	private void add(String iso3, ExternalService service, List<EntityEntry> found) {
		Optional<InformeaCountry> o = service.getMember(iso3);
		if (o.isPresent()) {
			InformeaCountry ic = o.get();
			found.add(new EntityEntry(ic.getIso3(), ic.getName(), 3l));
		}
	}

}
