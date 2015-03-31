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

import org.sharks.service.CountryService;
import org.sharks.service.ManagementEntityService;
import org.sharks.service.PoAService;
import org.sharks.service.dto.CountryDetails;
import org.sharks.service.dto.EntityDetails;
import org.sharks.service.dto.PoADetails;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/countries")
public class CountryResource {
	
	@Inject
	private CountryService service;
	
	@Inject
	private PoAService poaService;
	
	@Inject
	private ManagementEntityService entityService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CountryDetails> list(@DefaultValue("false") @QueryParam("onlyWithPoAs") String onlyWithPoAs) {
		boolean onlyWithPoAsFlag = Boolean.parseBoolean(onlyWithPoAs);
		return service.list(onlyWithPoAsFlag);
	}
	
	@GET
	@Path("{code}/poas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoADetails> getPoAs(@PathParam("code") String code) {
		return poaService.poasForCountry(code);
	}
	
	@GET
	@Path("{code}/entities")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityDetails> getEntities(@PathParam("code") String code) {
		return entityService.getEntitiesForCountry(code);
	}

}
