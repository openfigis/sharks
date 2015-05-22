/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;

import org.sharks.service.dto.CountryEntry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.domain.MgmtEntity;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryEntryProducer extends AbstractEntryProducer<MgmtEntity, CountryEntry> {
	
	@Inject
	private RefPubService refPubService;

	@Override
	public CountryEntry produce(MgmtEntity country) {
		
		String continent = null;
		
		RefPubCountry refPubCountry = refPubService.getCountry(country.getAcronym());
		if (refPubCountry!=null) continent = refPubCountry.getContinent();
		
		boolean hasPoAs = !country.getPoAs().isEmpty();
		
		return new CountryEntry(country.getAcronym(), country.getMgmtEntityName(), continent, hasPoAs);
	}

}
