/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.moniker.dto.FigisDoc;
import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.RfbEntry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class MonikersRestClient {

	private String restUrl;
	private MonikersParser parser;

	public MonikersRestClient(String restUrl) {
		this.restUrl = restUrl;
		this.parser = new MonikersParser();
	}

	public List<RfbEntry> getRfbs(String iso3Code) {
		try {
			URL rfb4iso3Url = getRfb4Iso3Url(iso3Code);
			log.trace("getting rfbs {} from {}", iso3Code, rfb4iso3Url);
			try (InputStream is = rfb4iso3Url.openStream()) {
				MonikerResponse<RfbEntry> response = parser.parseMonikerResponse(is);
				return response.getOutput().getItems();
			}
		} catch(Exception e) {
			throw new MonikerRestClientException("Error retrieving rfbs for "+iso3Code, e);
		}
	}
	
	public FigisDoc getFigisDoc(String figisId) {
		try {
			URL figisDocUrl = getFigisdocl(figisId);
			log.trace("getting figisDoc {} from {}", figisId, figisDocUrl);
			try (InputStream is = figisDocUrl.openStream()) {
				return parser.parseFigisDoc(is);
			}
		} catch(Exception e) {
			throw new MonikerRestClientException("Error retrieving figisDoc for "+figisId, e);
		}
	}
	
	private URL getRfb4Iso3Url(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"rfb4iso3/"+iso3Code);
	}
	
	private URL getFigisdocl(String figisId) throws MalformedURLException {
		return new URL(restUrl+"figisdoc/organization/"+figisId);
	}

	public class MonikerRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public MonikerRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
