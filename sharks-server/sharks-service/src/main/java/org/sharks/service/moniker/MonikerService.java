/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

import org.sharks.service.moniker.dto.FaoLexFiDocument;
import org.sharks.service.moniker.dto.Rfb;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface MonikerService {
	
	/**
	 * Returns a list of RFBs acronym for the specified country.
	 * @param countryIso3 the country ISO3 code.
	 * @return the list of RFB, an empty list if the country has not been found or the list of rfb is not available.
	 */
	public List<String> getRfbsForCountry(String countryIso3);
	
	/**
	 * Returns the {@link Rfb} associated to the specified RFB acronym.
	 * @param acronym the RFB acronym.
	 * @return the {@link Rfb} or <code>null</code> if not found.
	 */
	public Rfb getRfb(String acronym);
	
	/**
	 * Returns a list of {@link FaoLexFiDocument} for the specified country.
	 * @param countryIso3 the country iso3 code.
	 * @return the list of documents, an empty list if the country has not been found.
	 */
	public List<FaoLexFiDocument> getFaoLexDocumentsForCountry(String countryIso3);

}
