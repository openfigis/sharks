package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.dto.EntityDetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/managemententities")
public class ManagementEntityResource {
	
	@Inject
	private ManagementEntityService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityDetails> list() {
		return service.list();
	}

}
