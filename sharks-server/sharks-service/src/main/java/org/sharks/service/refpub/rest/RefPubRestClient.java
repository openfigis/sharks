/**
 * 
 */
package org.sharks.service.refpub.rest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.refpub.dto.RefPubCountries;
import org.sharks.service.refpub.dto.RefPubCountries.Link;
import org.sharks.service.refpub.dto.RefPubCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class RefPubRestClient {

	private String restUrl;
	private Unmarshaller unmarshaller;

	public RefPubRestClient(String restUrl) {
		this.restUrl = restUrl;

		try {
			JAXBContext context = JAXBContext.newInstance(RefPubCountry.class, RefPubCountries.class);
			unmarshaller = context.createUnmarshaller();
		} catch(Exception e) {
			throw new RefPubRestClientException("Error initializing JAXB", e);
		}
	}

	public RefPubCountry getCountry(String iso3Code) {
		try {
			URL countryUrl = getCountryUrl(iso3Code);
			log.trace("getting country {} from {}", iso3Code, countryUrl);
			try (InputStream is = countryUrl.openStream()) {
				RefPubCountry country = (RefPubCountry) unmarshaller.unmarshal(is);
				return country;
			}
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving country "+iso3Code, e);
		}
	}

	public List<RefPubCountry> listAllCountries() {
		try {
			List<RefPubCountry> countries = new ArrayList<RefPubCountry>();

			String nextPageUrl = restUrl+"concept/Country/xml?page=1";
			while(nextPageUrl!=null) {
				URL pageUrl = new URL(nextPageUrl);
				try (InputStream is = pageUrl.openStream()) {
					RefPubCountries pageCountries =  (RefPubCountries) unmarshaller.unmarshal(is);

					countries.addAll(pageCountries.getCountries());

					Link nextLink = pageCountries.getNextLink();
					nextPageUrl = nextLink!=null?nextLink.getHref():null;
				}
			}
			return countries;
		} catch(Exception e) {
			throw new RefPubRestClientException("Error retrieving countries list", e);
		}
	}


	//http://figisapps.fao.org/refpub-web/rest/concept/Country/codesystem/UN-ISO3/code/AFG/xml
	private URL getCountryUrl(String iso3Code) throws MalformedURLException {
		return new URL(restUrl+"concept/Country/codesystem/UN-ISO3/code/"+iso3Code+"/xml");
	}

	public class RefPubRestClientException extends RuntimeException {

		private static final long serialVersionUID = -3540294081975031895L;

		public RefPubRestClientException(String message, Throwable cause) {
			super(message, cause);
		}

	}

}
