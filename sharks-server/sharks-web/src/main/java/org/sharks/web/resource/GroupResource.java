package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.GroupService;
import org.sharks.service.dto.GroupDetails;
import org.sharks.service.dto.GroupEntry;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/groups")
@Api(value = "groups", description = "Operations about custom species groups")
public class GroupResource {
	
	@Inject
	private GroupService service;
	
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific custom species group by his code", response = GroupDetails.class)
	public GroupDetails get(
			@PathParam("code") 
			@ApiParam(value = "the group code", required = true)
			Long code) {
		return service.get(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the custom species groups", response = GroupEntry.class, responseContainer="List")
	public List<GroupEntry> list() {
		return service.list();
	}
}
