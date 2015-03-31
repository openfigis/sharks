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


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/species")
public class SpeciesResource {
	
	@Inject
	private SpeciesService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Species> list(@DefaultValue("false") @QueryParam("onlyWithMeasure") String onlyWithMeasure) {
		boolean onlyWithMeasureFlag = Boolean.parseBoolean(onlyWithMeasure);
		return service.list(onlyWithMeasureFlag);
	}
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Species get(@PathParam("code") String code) {
		return service.getSpecies(code);
	}

}
