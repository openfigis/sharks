/**
 * 
 */
package org.sharks.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.sharks.service.CachesService;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/caches")
@Api(value = "caches", description = "Operations about caches")
public class CacheResource {

	@Inject
	private CachesService service;
	
	@GET
	@Path("clear")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "clear the application caches. If the cache warmup is enabled it is done before operation return. The passphrase is required if set in the configuration file.", response = String.class)
	public String clear(
			@QueryParam("passphrase") 
			@ApiParam(value = "the passphrase set in the configuration file", required = false)
			String passphrase) {
		Boolean cleaned = service.clearCaches(passphrase);
		return cleaned?"The caches have been cleaned":"Caches not cleaned. Check if the specified passphrase is correct";
	}
}
