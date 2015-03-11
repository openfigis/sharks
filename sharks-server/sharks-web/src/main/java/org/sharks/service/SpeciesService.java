package org.sharks.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.dao.SharksDao;
import org.sharks.dao.model.Species;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/species")
public class SpeciesService {
	
	@Inject
	private SharksDao dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Species> list() {
		return dao.listAllSpecies();
	}
	
	@GET
	@Path("{code}")
	@Produces(MediaType.APPLICATION_JSON)
	public Species get(@PathParam("code") String code) {
		return dao.getSpecies(code);
	}

}
