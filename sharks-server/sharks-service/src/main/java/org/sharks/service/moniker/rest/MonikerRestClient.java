/**
 * 
 */
package org.sharks.service.moniker.rest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.moniker.dto.MonikerResponse;
import org.sharks.service.moniker.dto.Rfb;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class MonikerRestClient {

	private String restUrl;
	private Unmarshaller unmarshaller;

	public MonikerRestClient(String restUrl) {
		this.restUrl = restUrl;

		try {
			JAXBContext context = JAXBContext.newInstance(MonikerResponse.class, Rfb.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new MonikerRestClientException("Error initializing JAXB", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Rfb> getRfbs(String iso3Code) {
		try {
			URL rfb4iso3Url = getRfb4Iso3Url(iso3Code);
			log.trace("getting rfbs {} from {}", iso3Code, rfb4iso3Url);
			try (InputStream is = rfb4iso3Url.openStream()) {
				MonikerResponse<Rfb> response = (MonikerResponse<Rfb>) unmarshaller.unmarshal(is);
				return response.getOutput().getItems();
			}
		} catch(Exception e) {
			throw new MonikerRestClientException("Error retrieving rfbs for "+iso3Code, e);
		}
	}

	
	private URL getRfb4Iso3Url(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"rfb4iso3/"+iso3Code);
	}

	public class MonikerRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public MonikerRestClientException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
