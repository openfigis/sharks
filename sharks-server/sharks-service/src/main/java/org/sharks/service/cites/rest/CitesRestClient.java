/**
 * 
 */
package org.sharks.service.cites.rest;

import java.net.URL;

import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.cites.dto.CitesParties;
import org.sharks.service.http.HttpClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class CitesRestClient {

	private HttpClient httpClient;
	private String citesPartiesUrl;
	private CitesParser parser;
	
	public CitesRestClient(HttpClient httpClient, String citesPartiesUrl) {
		this.citesPartiesUrl = citesPartiesUrl;
		this.httpClient = httpClient;
		this.parser = new CitesParser();
	}
	
	public CitesParties getParties() {
		try {
			URL listUrl = new URL(citesPartiesUrl);
			
			log.trace("getting cites parties list from {}", listUrl);
			String content = httpClient.get(listUrl);
			
			CitesParties partiesList = parser.parseParties(content);
			return partiesList;
		} catch(Exception e) {
			throw new CitesParserRestClientException("Error retrieving cites parties list from "+citesPartiesUrl, e);
		}
	}

	
	public static class CitesParserRestClientException extends RuntimeException {

		private static final long serialVersionUID = -5952701531239934735L;

		public CitesParserRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
