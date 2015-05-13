/**
 * 
 */
package org.sharks.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.CountryDetails;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.util.ComplementUtil;
import org.sharks.storage.domain.Country;

import static org.sharks.service.EntryConverters.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class CountryComplementService {
	
	public static final String NOT_AVAILABLE_CODE = "N/A"; 
	
	@Inject
	private RefPubService refPubService;
	
	@Inject
	private ComplementUtil util;

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
	
	public List<CountryDetails> complement(List<Country> countries) {
		List<String> codes = countries.stream().filter(country->isAvailable(country)).map(Country::getCode).collect(Collectors.toList());
		Map<String, RefPubCountry> refPubCountries = refPubService.getCountries(codes);
		return countries.stream().map(c -> complement(c, refPubCountries.get(c.getCode()))).collect(Collectors.toList());
	}
	
	private boolean isAvailable(Country country) {
		return !country.getCode().equalsIgnoreCase(NOT_AVAILABLE_CODE);
	}
	
	private CountryDetails complement(Country country, RefPubCountry refPubCountry) {
		Map<String,String> officialNames = refPubCountry!=null?util.toNameMap(refPubCountry.getMultilingualOfficialName()):Collections.emptyMap();
		List<String> rfbs = refPubCountry!=null?refPubCountry.getFisheryCommissions():Collections.emptyList();
		return new CountryDetails(country.getCode(), 
				country.getUnName(), 
				officialNames, 
				rfbs,
				convert(country.getPoAs(), TO_POA_ENTRY));		
	}
	
}
