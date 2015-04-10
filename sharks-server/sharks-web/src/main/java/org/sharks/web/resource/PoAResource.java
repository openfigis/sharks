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

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/poa")
public class PoAResource {
	
	@Inject
	private PoAService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoAEntry> list() {
		return service.list();
	}
	
	@GET
	@Path("{poa}")
	@Produces(MediaType.APPLICATION_JSON)
	public PoA get(@PathParam("poa") Long code) {
		return service.get(code);
	}

}
