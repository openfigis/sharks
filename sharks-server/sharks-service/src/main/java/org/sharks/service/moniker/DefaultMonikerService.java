/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.moniker.dto.RfbEntry;
import org.sharks.service.moniker.rest.MonikersRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DefaultMonikerService implements MonikerService {
	
	@Inject
	private MonikersRestClient restClient;

	@Override
	public List<RfbEntry> getRfbs(String countryIso3) {
		return restClient.getRfbs(countryIso3);
	}

}
