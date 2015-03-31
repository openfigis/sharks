/**
 * 
 */
package org.sharks.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.dto.CountryDetails;
import org.sharks.service.refpub.RefPubService;
import org.sharks.service.refpub.dto.MultiLingualName;
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
		
		RefPubCountry refPubCountry = isAvailable(country)?refPubService.getCountry(country.getCode()):null;
		return complement(country, refPubCountry);
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
		Map<String,String> officialNames = refPubCountry!=null?toNameMap(refPubCountry.getMultilingualOfficialName()):Collections.emptyMap();
		return new CountryDetails(country.getCode(), country.getUnName(), officialNames);		
	}
	
	private Map<String, String> toNameMap(MultiLingualName name) {
		Map<String, String> names = new HashMap<String, String>();
		names.put("en", name.getEnglish());
		names.put("fr", name.getFrench());
		names.put("es", name.getSpanish());
		names.put("ar", name.getArabic());
		names.put("zh", name.getChinese());
		names.put("ru", name.getRussian());
		return names;
	}
}
