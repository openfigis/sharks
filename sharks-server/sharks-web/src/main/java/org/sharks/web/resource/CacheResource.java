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

import lombok.extern.slf4j.Slf4j;

import org.sharks.config.Configuration;
import org.sharks.service.CacheService;
import org.sharks.service.dto.ClearCacheStatus;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@ApplicationScoped
@Path(CacheResource.CACHES_PATH)
@Api(value = "caches", description = "Operations about caches")
public class CacheResource {
	
	public static final String CACHES_PATH = "/caches";

	@Inject
	private CacheService service;

	@Inject
	private Configuration configuration;

	@POST
	@Path("clear")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "clear the application caches. The passphrase is required if set in the configuration file.", response = ClearCacheStatus.class)
	public ClearCacheStatus clear(
			@QueryParam("passphrase") 
			@ApiParam(value = "the passphrase set in the configuration file", required = false)
			String passphrase) {


		if (configuration.getCacheCleaningPassphrase()!=null 
				&& !configuration.getCacheCleaningPassphrase().isEmpty()
				&& !configuration.getCacheCleaningPassphrase().equals(passphrase)) {
			
			log.warn("Attempt to clean the cache with a wrong passphrase {}", passphrase);

			throw new WebApplicationException(401);
		}

		service.asyncClearExternalCaches();

		return service.getClearCacheStatus();
	}

	@GET
	@Path("status")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns the clean cache status.", response = ClearCacheStatus.class)
	public ClearCacheStatus status() {
		return service.getClearCacheStatus();
	}

}
