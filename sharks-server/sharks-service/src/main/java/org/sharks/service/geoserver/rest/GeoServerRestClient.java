/**
 * 
 */
package org.sharks.service.geoserver.rest;

import java.net.URL;

import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.geoserver.dto.SpeciesList;
import org.sharks.service.http.HttpClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class GeoServerRestClient {

	private HttpClient httpClient;
	private String speciesListUrl;
	private GeoServerParser parser;
	
	public GeoServerRestClient(HttpClient httpClient, String speciesListUrl) {
		this.speciesListUrl = speciesListUrl;
		this.httpClient = httpClient;
		this.parser = new GeoServerParser();
	}
	
	public SpeciesList getSpeciesList() {
		try {
			URL listUrl = new URL(speciesListUrl);
			
			log.trace("getting geoServer species list from {}", listUrl);
			String content = httpClient.get(listUrl);
			
			SpeciesList speciesList = parser.parseSpeciesList(content);
			return speciesList;
		} catch(Exception e) {
			throw new GeoServerRestClientException("Error retrieving species list from "+speciesListUrl, e);
		}
	}

	
	public static class GeoServerRestClientException extends RuntimeException {

		private static final long serialVersionUID = -5952701531239934735L;

		public GeoServerRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
