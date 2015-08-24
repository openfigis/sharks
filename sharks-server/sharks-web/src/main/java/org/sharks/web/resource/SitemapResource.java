package org.sharks.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.web.sitemap.SitemapService;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/sitemap")
@Api(value = "sitemap", description = "Operations about sitemap")
public class SitemapResource {
	
	@Inject
	private SitemapService service;
	
	@GET
	@Produces(MediaType.TEXT_XML)
	@ApiOperation(value = "returns the sitemap", response = String.class)
	public String get() {
		return service.getSitemap();
	}


}
