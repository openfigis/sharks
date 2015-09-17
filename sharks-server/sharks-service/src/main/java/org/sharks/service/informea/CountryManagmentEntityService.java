package org.sharks.service.informea;

import java.util.List;

import org.sharks.service.dto.EntityEntry;

public interface CountryManagmentEntityService {

	public List <EntityEntry> getMesForCountry(String iso3);

}
