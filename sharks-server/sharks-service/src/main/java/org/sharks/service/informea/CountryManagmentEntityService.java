package org.sharks.service.informea;

import java.util.List;

import org.sharks.service.dto.EntityDetails;

public interface CountryManagmentEntityService {

	public List <EntityDetails> getMesForCountry(String iso3);

}
