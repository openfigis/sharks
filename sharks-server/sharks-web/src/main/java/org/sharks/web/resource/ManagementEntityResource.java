package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.MeasureService;
import org.sharks.service.dto.EntityEntry;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.domain.Country;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/managemententities")
@Api(value = "management entities", description = "Operations about management entities")
public class ManagementEntityResource {
	
	@Inject
	private ManagementEntityService service;
	
	@Inject
	private MeasureService measureService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the management entities", response = EntityEntry.class, responseContainer="List")
	public List<EntityEntry> list() {
		return service.list();
	}
	
	@GET
	@Path("{acronym}/measures")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the measures related to the specified management entity", response = MeasureEntry.class, responseContainer="List")
	public List<MeasureEntry> getMeasures(
			@PathParam("acronym") 
			@ApiParam(value = "the management entity acronym", required = true)
			String acronym) {
		return measureService.measuresForManagementEntity(acronym);
	}
	
	@GET
	@Path("{acronym}/countries")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the countries related to the specified management entity", response = Country.class, responseContainer="List")
	public List<Country> getCountries(
			@PathParam("acronym")
			@ApiParam(value = "the management entity acronym", required = true)
			String acronym) {
		return service.getCountries(acronym);
	}

}
