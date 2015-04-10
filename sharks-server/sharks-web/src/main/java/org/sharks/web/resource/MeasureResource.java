package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.sharks.service.MeasureService;
import org.sharks.service.dto.EntityMeasures;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.domain.Measure;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/measures")
public class MeasureResource {
	
	@Inject
	private MeasureService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MeasureEntry> list() {
		return service.list();
	}
	
	@GET
	@Path("{measure}")
	@Produces(MediaType.APPLICATION_JSON)
	public Measure get(@PathParam("measure") String id) {
		return service.get(id);
	}
	
	@GET
	@Path("groupByEntity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<EntityMeasures> grouppedByEntity(@QueryParam("speciesAlphaCodes") List<String> speciesAlphaCodes) {
		return service.measuresForSpeciesByEntity(speciesAlphaCodes);
	}

}
