package org.sharks.web.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.SharksService;
import org.sharks.storage.domain.Species;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/species")
public class SpeciesService {
	
	@Inject
	private SharksService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Species> list() {
		return service.listAllSpecies();
	}
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Species get(@PathParam("code") String code) {
		return service.getSpecies(code);
	}

}
