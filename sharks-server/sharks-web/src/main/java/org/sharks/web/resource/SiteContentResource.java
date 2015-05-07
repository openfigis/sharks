/**
 * 
 */
package org.sharks.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.SiteContentService;
import org.sharks.service.dto.SiteContent;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/contents")
@Api(value = "contents", description = "Operations about site content")
public class SiteContentResource {

	@Inject
	private SiteContentService service;
	
	@GET
	@Path("{keyword}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get the site content with the specified keyword", response = SiteContent.class)
	public SiteContent get(
			@PathParam("keyword") 
			@ApiParam(value = "the content keyword", required = true)
			String keyword) {
		return service.get(keyword);
	}
}
