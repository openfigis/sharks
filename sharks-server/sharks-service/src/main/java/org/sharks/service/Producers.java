/**
 * 
 */
package org.sharks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.sharks.service.moniker.rest.MonikerRestClient;
import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class Producers {
	
	@Produces
	public RefPubRestClient getRefPubRestClient() {
		return new RefPubRestClient("http://figisapps.fao.org/refpub-web/rest/");
	}
	
	@Produces
	public MonikerRestClient getMonikerRestClient() {
		return new MonikerRestClient("http://figisapps.fao.org/figis/moniker/");
	}

}
