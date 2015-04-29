package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.PoAService;
import org.sharks.service.dto.PoAEntry;
import org.sharks.storage.domain.PoA;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/poa")
@Api(value = "poas", description = "Operations about PoAs")
public class PoAResource {
	
	@Inject
	private PoAService service;
	
	@GET
	@Path("{poa}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific PoA by his code", response = PoA.class)
	public PoA get(
			@PathParam("poa") 
			@ApiParam(value = "the PoA code", required = true)
			Long code) {
		return service.get(code);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the PoAs", response = PoAEntry.class, responseContainer="List")
	public List<PoAEntry> list() {
		return service.list();
	}

}
