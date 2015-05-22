package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.sharks.service.CountryService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import static org.sharks.web.util.ResourceUtils.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/countries")
@Api(value = "countries", description = "Operations about countries")
public class CountryResource {
	
	@Inject
	private CountryService service;
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific country by his code", response = CountryDetails.class)
	public CountryDetails get(
			@PathParam("code") 
			@ApiParam(value = "the country code", required = true)
			String code) {
		return checkNotFound(service.get(code));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the countries", notes="the list of countries can be filtered through the onlyWithPoAs flag", response = CountryEntry.class, responseContainer="List")
	public List<CountryEntry> list(
			@DefaultValue("false") 
			@QueryParam("onlyWithPoAs") 
			@ApiParam(value = "a flag to select only countries with almost one PoA associated", required = false)
			boolean onlyWithPoAs) {
		return service.list(onlyWithPoAs);
	}

}
