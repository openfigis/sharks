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

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.EntityEntry;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import static org.sharks.web.util.ResourceUtils.*;

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
	
	@GET
	@Path("{acronym}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific entity by his acronym", response = EntityDetails.class)
	public EntityDetails get(
			@PathParam("acronym") 
			@ApiParam(value = "the entity acronym", required = true)
			String acronym) {
		return checkNotFound(service.get(acronym));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the management entities", notes="the list of entities can be filtered through the onlyWithMeasures flag", response = EntityEntry.class, responseContainer="List")
	public List<EntityEntry> list(
			@DefaultValue("false") 
			@QueryParam("onlyWithMeasures") 
			@ApiParam(value = "a flag to select only entities with almost one Measure associated", required = false)
			boolean onlyWithMeasures) {
		return service.list(onlyWithMeasures);
	}
}
