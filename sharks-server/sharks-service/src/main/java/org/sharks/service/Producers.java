/**
 * 
 */
package org.sharks.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.sharks.service.refpub.rest.RefPubRestClient;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
public class Producers {
	
	@Produces
	public RefPubRestClient getClient() {
		return new RefPubRestClient("http://figisapps.fao.org/refpub-web/rest/");
	}

}
