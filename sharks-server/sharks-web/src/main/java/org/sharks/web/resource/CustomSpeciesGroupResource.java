package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.CustomSpeciesGroupService;
import org.sharks.storage.domain.CustomSpeciesGrp;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/groups")
@Api(value = "groups", description = "Operations about custom species groups")
public class CustomSpeciesGroupResource {
	
	@Inject
	private CustomSpeciesGroupService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the custom species groups", response = CustomSpeciesGrp.class, responseContainer="List")
	public List<CustomSpeciesGrp> list() {
		return service.list();
	}
}
