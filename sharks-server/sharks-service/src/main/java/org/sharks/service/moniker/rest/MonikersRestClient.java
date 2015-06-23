/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.http.HttpClient;
import org.sharks.service.moniker.dto.ErrorElement;
import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.MonikerResponse.Output;
import org.sharks.service.moniker.dto.Rfb;

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
	public List<Rfb> getRfb4Iso3(String iso3Code) {
		try {
			URL rfb4iso3Url = getRfb4Iso3Url(iso3Code);
			
			log.trace("getting rfbs {} from {}", iso3Code, rfb4iso3Url);
			String content = httpClient.get(rfb4iso3Url);
			
			MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
			Output<Rfb> output = response.getOutput();
			return output.getItems();
			
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving rfbs for "+iso3Code, e);
		}
	}
	
	public Rfb getRfbByFid(String fid) {
		return getRfb(fid);
	}
	
	public Rfb getRfbByAcronym(String acronym) {
		return getRfb(acronym);
	}
	
	private Rfb getRfb(String id) {
		try {
			URL rfbUrl = getRfbUrl(id);
			
			log.trace("getting rfb {} from {}", id, rfbUrl);
			String content = httpClient.get(rfbUrl);
			
			MonikerResponse<Rfb> response = parser.parseMonikerResponse(content);
			Output<Rfb> output = response.getOutput();
			if (output.getItems()!=null && !output.getItems().isEmpty()) return output.getItems().get(0);
			return null;
			
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving rfb with identifier "+id, e);
		}
	}
	
	public List<FaoLexFiDocument> getFaoLexDocuments(String iso3) {
		try {
			URL faolexFiDocUrl = getFaoLexFIUrl(iso3);
			log.trace("getting FaoLexDocuments for {} from {}", iso3, faolexFiDocUrl);
			
			String content = httpClient.get(faolexFiDocUrl);
			if (content.contains("<root error")) return null;
			
			MonikerResponse<FaoLexFiDocument> response = parser.parseMonikerResponse(content);
			Output<FaoLexFiDocument> output = response.getOutput();
			if (isErrorResponse(output.getItems())) return null;
			
			return output.getItems();
		} catch(Exception e) {
			throw new MonikersRestClientException("Error retrieving FaoLexDocuments for "+iso3, e);
		}
	}
	
	private <T> boolean isErrorResponse(List<T> items) {
		return items.size() == 1 && items.get(0) instanceof ErrorElement;
	}
	
	private URL getRfb4Iso3Url(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"rfb4iso3/"+iso3Code);
	}
	
	private URL getRfbUrl(String id) throws MalformedURLException {
		return new URL(restUrl+"rfb/"+id);
	}
	
	private URL getFaoLexFIUrl(String iso3) throws MalformedURLException {
		return new URL(restUrl+"faolexfi/kwid=089/iso3="+iso3);
	}

	public static class MonikersRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public MonikersRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
