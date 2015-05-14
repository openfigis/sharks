/**
 * 
 */
package org.sharks.service.refpub;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class RefPubServiceImpl implements RefPubService {

	@Inject
	private RefPubRestClient restClient;

	//TODO cache

	@Override
	public RefPubCountry getCountry(String iso3Code) {
		try {
			RefPubCountry country = restClient.getCountry(iso3Code);
			return country;
		} catch(Exception e) {
			log.error("Error getting country "+iso3Code+" from refPub", e);
			return null;
		}
	}

	@Override
	public RefPubSpecies getSpecies(String alpha3Code) {
		try {
			RefPubSpecies species = restClient.getSpecies(alpha3Code);
			return species;
		} catch(Exception e) {
			log.error("Error getting species "+alpha3Code+" from refPub", e);
			return null;
		}
	}
}
