/**
 * 
 */
package org.sharks.service.kor.rest;

import java.net.MalformedURLException;
import java.net.URL;

import javax.inject.Singleton;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.http.HttpClient;
import org.sharks.service.kor.dto.KorResource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j @Singleton
public class KorRestClient {

	private HttpClient httpClient;
	private String endpointUrl;
	private KorParser parser;
	
	public KorRestClient(HttpClient httpClient, String korUrl, KorParser parser) {
		this.endpointUrl = korUrl;
		this.httpClient = httpClient;
		this.parser = parser;
	}
	
	public KorResource getResource(String id) {
		try {
			URL resourceUrl = getResourceUrl(id);
			
			log.trace("getting resource {} from {}", id, resourceUrl);
			String content = httpClient.get(resourceUrl);
			
			KorResource resource = parser.parseResource(content);
			return resource;
		} catch(Exception e) {
			throw new KorParserRestClientException("Error retrieving resource with id "+id, e);
		}
	}
	
	private URL getResourceUrl(String id) throws MalformedURLException {
		return new URL(endpointUrl+id);
	}

	
	public static class KorParserRestClientException extends RuntimeException {

		private static final long serialVersionUID = -5952701531239934735L;

		public KorParserRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
