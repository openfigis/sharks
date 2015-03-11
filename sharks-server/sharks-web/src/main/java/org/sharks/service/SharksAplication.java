/**
 * 
 */
package org.sharks.service;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationPath("")
public class SharksAplication extends ResourceConfig {
	
	public SharksAplication() {
		packages("org.sharks.service");
	}

}