/**
 * 
 */
package org.sharks.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import org.sharks.service.CacheService;
import org.sharks.service.CacheService.ClearCacheStatus;
import org.sharks.service.CacheService.WrongPasswordException;

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
	private CacheService service;
	
	@POST
	@Path("clear")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "clear the application caches. The passphrase is required if set in the configuration file.", response = ClearCacheStatus.class)
	public ClearCacheStatus clear(
			@QueryParam("passphrase") 
			@ApiParam(value = "the passphrase set in the configuration file", required = false)
			String passphrase) {
		try {
			return service.clearCaches(passphrase);
		} catch(WrongPasswordException e) {
			throw new WebApplicationException(401);
		}
	}
	
	@GET
	@Path("status")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns the clean cache status.", response = ClearCacheStatus.class)
	public ClearCacheStatus status() {
		return service.getClearCacheStatus();
	}

}
