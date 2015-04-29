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
import org.sharks.service.ManagementEntityService;
import org.sharks.service.PoAService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.CountryEntry;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.PoAEntry;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

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
	
	@Inject
	private PoAService poaService;
	
	@Inject
	private ManagementEntityService entityService;
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific country by his code", response = CountryDetails.class)
	public CountryDetails get(
			@PathParam("code") 
			@ApiParam(value = "the country code", required = true)
			String code) {
		return service.get(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the countries", notes="the list of countries can be filtered through the onlyWithPoAs flag", response = CountryEntry.class, responseContainer="List")
	public List<CountryEntry> list(
			@DefaultValue("false") 
			@QueryParam("onlyWithPoAs") 
			@ApiParam(value = "a flag to select only countries connected to a PoA", required = false)
			String onlyWithPoAs) {
		boolean onlyWithPoAsFlag = Boolean.parseBoolean(onlyWithPoAs);
		return service.list(onlyWithPoAsFlag);
	}
	
	@GET
	@Path("{code}/poas")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the PoAs related to the specified country", response = PoAEntry.class, responseContainer="List")
	public List<PoAEntry> getPoAs(
			@PathParam("code") 
			@ApiParam(value = "the country code", required = true)
			String code) {
		return poaService.poasForCountry(code);
	}
	
	@GET
	@Path("{code}/entities")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the management entities related to the specified country", response = EntityEntry.class, responseContainer="List")
	public List<EntityEntry> getEntities(
			@PathParam("code") 
			@ApiParam(value = "the country code", required = true)
			String code) {
		return entityService.getEntitiesForCountry(code);
	}

}
