package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.CountryService;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/countries")
public class CountryResource {
	
	@Inject
	private CountryService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> list() {
		return service.list();
	}

}
