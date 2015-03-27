package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.ManagementEntityService;
import org.sharks.service.MeasureService;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.MeasureDetails;
import org.sharks.storage.domain.Country;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/managemententities")
public class ManagementEntityResource {
	
	@Inject
	private ManagementEntityService service;
	
	@Inject
	private MeasureService measureService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityDetails> list() {
		return service.list();
	}
	
	@GET
	@Path("{acronym}/measures")
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeasureDetails> getMeasures(@PathParam("acronym") String acronym) {
		return measureService.measuresForManagementEntity(acronym);
	}
	
	@GET
	@Path("{acronym}/countries")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Country> getCountries(@PathParam("acronym") String acronym) {
		return service.getCountries(acronym);
	}

}
