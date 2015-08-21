/**
 * 
 */
package org.sharks.service.refpub.rest;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.http.HttpClient;
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
	private HttpClient httpClient;

	public RefPubRestClient(String restUrl, HttpClient httpClient) {
		this.restUrl = restUrl;
		this.httpClient = httpClient;
		this.parser = new RefPubParser();
	}

	public RefPubCountry getCountryByIso3(String iso3Code) {
		try {
			URL countryUrl = getCountryUrlFromIso3(iso3Code);
			
			log.trace("getting country {} from {}", iso3Code, countryUrl);
			String content = httpClient.get(countryUrl);
			
			RefPubCountry country = parser.parseCountry(content);
			return country;
			
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving country "+iso3Code, e);
		}
	}
	
	public RefPubSpecies getSpecies(String alpha3Code) {
		try {
			URL speciesUrl = getSpeciesUrl(alpha3Code);
			
			log.trace("getting species {} from {}", alpha3Code, speciesUrl);
			String content = httpClient.get(speciesUrl);
			
			RefPubSpecies species = parser.parseSpecies(content);
			return species;
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving species "+alpha3Code, e);
		}
	}

	//http://figisapps.fao.org/refpub-web/rest/concept/Country/codesystem/UN-ISO3/code/AFG/xml
	private URL getCountryUrlFromIso3(String iso3Code) throws MalformedURLException, UnsupportedEncodingException {
		return new URL(restUrl+"concept/Country/codesystem/UN-ISO3/code/"+URLEncoder.encode(iso3Code, "UTF-8")+"/xml");
	}
	
	//http://figisapps.fao.org/refpub-web/rest/concept/Country/codesystem/UN-ISO3/code/AFG/xml
	private URL getSpeciesUrl(String alpha3Code) throws MalformedURLException, UnsupportedEncodingException {
		return new URL(restUrl+"concept/Species/codesystem/ASFIS/code/"+URLEncoder.encode(alpha3Code, "UTF-8")+"/xml");
	}

	public static class RefPubRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public RefPubRestClientException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
