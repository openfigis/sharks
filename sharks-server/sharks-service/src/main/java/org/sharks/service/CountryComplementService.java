/**
 * 
 */
package org.sharks.service;

import static org.sharks.service.producer.EntryProducers.TO_POA_ENTRY;
import static org.sharks.service.producer.EntryProducers.convert;
import static org.sharks.service.util.ConversionUtil.toNamesMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.CountryDetails;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class CountryComplementService {
	
	public static final String NOT_AVAILABLE_CODE = "N/A"; 
	
	@Inject
	private RefPubService refPubService;

	public CountryDetails complement(Country country) {
		
		RefPubCountry refPubCountry = getCountry(country);
		return complement(country, refPubCountry);
	}
	
	private RefPubCountry getCountry(Country country) {
		return isAvailable(country)?refPubService.getCountry(country.getCode()):null;
	}
	
	public String getContinent(Country country) {
		RefPubCountry refPubCountry = getCountry(country);
		return refPubCountry!=null?refPubCountry.getContinent():null;
	}
	
	private boolean isAvailable(Country country) {
		return !country.getCode().equalsIgnoreCase(NOT_AVAILABLE_CODE);
	}
	
	private CountryDetails complement(Country country, RefPubCountry refPubCountry) {
		Map<String,String> officialNames = refPubCountry!=null?toNamesMap(refPubCountry.getMultilingualOfficialName()):Collections.emptyMap();
		List<String> rfbs = refPubCountry!=null?refPubCountry.getFisheryCommissions():Collections.emptyList();
		return new CountryDetails(country.getCode(), 
				country.getUnName(), 
				officialNames, 
				rfbs,
				convert(country.getPoAs(), TO_POA_ENTRY));		
	}
	
}
