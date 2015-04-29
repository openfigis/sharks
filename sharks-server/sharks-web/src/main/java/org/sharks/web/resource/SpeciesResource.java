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

import org.sharks.service.SpeciesService;
import org.sharks.storage.domain.Species;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/species")
@Api(value = "species", description = "Operations about species")
public class SpeciesResource {
	
	@Inject
	private SpeciesService service;
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific species by his code", response = Species.class)
	public Species get(
			@PathParam("code") 
			@ApiParam(value = "the species code", required = true)
			String code) {
		return service.getSpecies(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the species", response = Species.class, responseContainer="List")
	public List<Species> list(
			@DefaultValue("false") 
			@QueryParam("onlyWithMeasure") 
			@ApiParam(value = "select only the species related to a measure", required = false)
			String onlyWithMeasure) {
		boolean onlyWithMeasureFlag = Boolean.parseBoolean(onlyWithMeasure);
		return service.list(onlyWithMeasureFlag);
	}
}
