/**
 * 
 */
package org.sharks.service.informea.rest;

import java.net.URL;

import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.http.HttpClient;
import org.sharks.service.informea.dto.InformeaParties;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class InformeaRestClient {

	private HttpClient httpClient;
	private String endpointUrl;
	private InformeaParser parser;
	
	public InformeaRestClient(HttpClient httpClient, String informeaEndpoint) {
		this.endpointUrl = informeaEndpoint;
		this.httpClient = httpClient;
		this.parser = new InformeaParser();
	}
	
	public InformeaParties getParties() {
		try {
			URL listUrl = new URL(endpointUrl);
			
			log.trace("getting parties list from {}", listUrl);
			String content = httpClient.get(listUrl);
			
			InformeaParties partiesList = parser.parseParties(content);
			return partiesList;
		} catch(Exception e) {
			throw new InformeaParserRestClientException("Error retrieving parties list from "+endpointUrl, e);
		}
	}

	
	public static class InformeaParserRestClientException extends RuntimeException {

		private static final long serialVersionUID = -5952701531239934735L;

		public InformeaParserRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
