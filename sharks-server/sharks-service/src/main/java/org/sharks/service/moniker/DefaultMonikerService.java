/**
 * 
 */
package org.sharks.service.moniker;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.moniker.dto.Rfb;
import org.sharks.service.moniker.rest.MonikerRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DefaultMonikerService implements MonikerService {
	
	@Inject
	private MonikerRestClient restClient;

	@Override
	public List<Rfb> getRfbs(String countryIso3) {
		return restClient.getRfbs(countryIso3);
	}

}
