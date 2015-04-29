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

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Api(value = "/countries", description = "Operations about countries")
@Path("/countries")
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
	@ApiOperation(value = "Get a specific Country by code", response = CountryDetails.class)
	public CountryDetails get(@PathParam("code") String code) {
		return service.get(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CountryEntry> list(@DefaultValue("false") @QueryParam("onlyWithPoAs") String onlyWithPoAs) {
		boolean onlyWithPoAsFlag = Boolean.parseBoolean(onlyWithPoAs);
		return service.list(onlyWithPoAsFlag);
	}
	
	@GET
	@Path("{code}/poas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoAEntry> getPoAs(@PathParam("code") String code) {
		return poaService.poasForCountry(code);
	}
	
	@GET
	@Path("{code}/entities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityEntry> getEntities(@PathParam("code") String code) {
		return entityService.getEntitiesForCountry(code);
	}

}
