/**
 * 
 */
package org.sharks.web;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;

import lombok.extern.slf4j.Slf4j;

import org.glassfish.jersey.server.ResourceConfig;
import org.sharks.config.Configuration;
import org.sharks.web.util.CorsFilter;
import org.sharks.web.util.GenericExceptionMapper;

import com.wordnik.swagger.jaxrs.config.BeanConfig;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationPath("rest")
@Slf4j
public class SharksAplication extends ResourceConfig {
	
	@Inject
	private Configuration configuration;
	
	public SharksAplication() {
		
		packages("org.sharks.web.resource");
		packages("com.wordnik.swagger.jaxrs.listing");
		
		register(new CorsFilter());
		register(new GenericExceptionMapper());
	}
	
	@PostConstruct
	private void buildBeanConfig() {
        URL restUrl = configuration.getSharksRestUrl();
        
        if (restUrl == null) {
        	log.warn("Missing sharks url in configuration, swagger will be not available");
        	return;
        }
        	
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Sharks rest API documentation");
        beanConfig.setVersion("1.0.0");
  
        beanConfig.setSchemes(new String[]{restUrl.getProtocol()});
        String host = restUrl.getHost();
        if (restUrl.getPort()>0) host = host+":"+restUrl.getPort();
        beanConfig.setHost(host);
        String path = restUrl.getPath();
        if (path.endsWith("/")) path = path.substring(0, path.length()-1);
        beanConfig.setBasePath(path);
        
        beanConfig.setResourcePackage("org.sharks.web.resource");
        beanConfig.setScan(true);
	}

}