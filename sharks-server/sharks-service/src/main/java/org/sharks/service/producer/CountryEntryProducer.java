/**
 * 
 */
package org.sharks.service.producer;

import javax.inject.Inject;

import org.sharks.service.dto.CountryEntry;
import org.sharks.service.producer.EntryProducers.AbstractEntryProducer;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class CountryEntryProducer extends AbstractEntryProducer<Country, CountryEntry> {
	
	@Inject
	private RefPubService refPubService;

	@Override
	public CountryEntry produce(Country country) {
		
		String continent = null;
		
		RefPubCountry refPubCountry = refPubService.getCountry(country.getCode());
		if (refPubCountry!=null) continent = refPubCountry.getContinent();
		
		return new CountryEntry(country.getCode(), country.getUnName(), continent);
	}

}
