/**
 * 
 */
package org.sharks.service.refpub.rest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.refpub.dto.RefPubCountry;
import org.sharks.service.refpub.dto.RefPubSpecies;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class RefPubRestClient {

	private String restUrl;
	private RefPubParser parser;

	public RefPubRestClient(String restUrl) {
		this.restUrl = restUrl;
		this.parser = new RefPubParser();
	}

	public RefPubCountry getCountry(String iso3Code) {
		try {
			URL countryUrl = getCountryUrl(iso3Code);
			log.trace("getting country {} from {}", iso3Code, countryUrl);
			try (InputStream is = countryUrl.openStream()) {
				RefPubCountry country = parser.parseCountry(is);
				return country;
			}
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving country "+iso3Code, e);
		}
	}
	
	public RefPubSpecies getSpecies(String alpha3Code) {
		try {
			URL speciesUrl = getSpeciesUrl(alpha3Code);
			log.trace("getting species {} from {}", alpha3Code, speciesUrl);
			try (InputStream is = speciesUrl.openStream()) {
				RefPubSpecies species = parser.parseSpecies(is);
				return species;
			}
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving species "+alpha3Code, e);
		}
	}

	//http://figisapps.fao.org/refpub-web/rest/concept/Country/codesystem/UN-ISO3/code/AFG/xml
	private URL getCountryUrl(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"concept/Country/codesystem/UN-ISO3/code/"+iso3Code+"/xml");
	}
	
	//http://figisapps.fao.org/refpub-web/rest/concept/Country/codesystem/UN-ISO3/code/AFG/xml
	private URL getSpeciesUrl(String alpha3Code) throws MalformedURLException {
		return new URL(restUrl+"concept/Species/codesystem/ASFIS/code/"+alpha3Code+"/xml");
	}

	public class RefPubRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public RefPubRestClientException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
