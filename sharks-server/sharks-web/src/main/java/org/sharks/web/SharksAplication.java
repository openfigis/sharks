/**
 * 
 */
package org.sharks.web;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.sharks.web.util.CorsFilter;
import org.sharks.web.util.GenericExceptionMapper;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationPath("rest")
public class SharksAplication extends ResourceConfig {
	
	public SharksAplication() {
		
		packages("org.sharks.web.resource");
		packages("com.wordnik.swagger.jaxrs.listing");
		
		register(new CorsFilter());
		register(new GenericExceptionMapper());
        
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Sharks rest API documentation");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/sharks-web/rest");
        beanConfig.setResourcePackage("org.sharks.web.resource");
        beanConfig.setScan(true);
	}

}