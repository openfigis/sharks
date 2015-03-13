/**
 * 
 */
package org.sharks.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.sharks.web.util.CorsFilter;
import org.sharks.web.util.GenericExceptionMapper;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationPath("")
public class SharksAplication extends ResourceConfig {
	
	public SharksAplication() {
		packages("org.sharks.web.service");
		register(new CorsFilter());
		register(new GenericExceptionMapper());
	}

}