/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.http.HttpClient;
import org.sharks.service.moniker.dto.FaoLexDocument;
import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.MonikerResponse.Output;
import org.sharks.service.moniker.dto.RfbEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class MonikersRestClient {

	private String restUrl;
	private MonikersParser parser;
	private HttpClient httpClient;

	public MonikersRestClient(String restUrl, HttpClient httpClient) {
		this.restUrl = restUrl;
		this.httpClient = httpClient;
		this.parser = new MonikersParser();
	}

	/**
	 * Calls the rfb4iso3 moniker with the given iso3code.
	 * @param iso3Code the country iso3code.
	 * @return the list of rfbs retrieved.
	 */
	public List<RfbEntry> getRfb4Iso3(String iso3Code) {
		try {
			URL rfb4iso3Url = getRfb4Iso3Url(iso3Code);
			
			log.trace("getting rfbs {} from {}", iso3Code, rfb4iso3Url);
			String content = httpClient.get(rfb4iso3Url);
			
			MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
			Output<RfbEntry> output = response.getOutput();
			return output.getItems();
			
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving rfbs for "+iso3Code, e);
		}
	}
	
	public RfbEntry getRfb(String acronym) {
		try {
			URL rfbUrl = getRfbUrl(acronym);
			
			log.trace("getting rfb {} from {}", acronym, rfbUrl);
			String content = httpClient.get(rfbUrl);
			
			MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(content);
			Output<RfbEntry> output = response.getOutput();
			if (output.getItems()!=null && !output.getItems().isEmpty()) return output.getItems().get(0);
			return null;
			
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving rfb for "+acronym, e);
		}
	}
	
	public FigisDoc getFigisDoc(String figisId) {
		try {
			URL figisDocUrl = getFigisdocUrl(figisId);
			log.trace("getting figisDoc {} from {}", figisId, figisDocUrl);
			
			String content = httpClient.get(figisDocUrl);
			if (content.contains("<root error")) return null;
			
			return parser.parseFigisDoc(content);
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving figisDoc for "+figisId, e);
		}
	}
	
	public List<FaoLexDocument> getFaoLexDocuments(String iso3) {
		try {
			URL faolexFiDocUrl = getFaoLexFIUrl(iso3);
			log.trace("getting FaoLexDocuments for {} from {}", iso3, faolexFiDocUrl);
			
			String content = httpClient.get(faolexFiDocUrl);
			if (content.contains("<root error")) return null;
			
			MonikerResponse<FaoLexDocument> response = parser.parseMonikerResponse(content);
			Output<FaoLexDocument> output = response.getOutput();
			return output.getItems();
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving FaoLexDocuments for "+iso3, e);
		}
	}
	
	private URL getRfb4Iso3Url(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"rfb4iso3/"+iso3Code);
	}
	
	private URL getRfbUrl(String acronym) throws MalformedURLException {
		return new URL(restUrl+"rfb/"+acronym);
	}
	
	private URL getFigisdocUrl(String figisId) throws MalformedURLException {
		return new URL(restUrl+"figisdoc/organization/"+figisId);
	}
	
	private URL getFaoLexFIUrl(String iso3) throws MalformedURLException {
		return new URL(restUrl+"faolexfi/kwid=55/iso3="+iso3);
	}

	public static class MonikersRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public MonikersRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
