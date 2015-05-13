package org.sharks.web.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.sharks.service.MeasureService;
import org.sharks.service.dto.MeasureEntry;
import org.sharks.storage.domain.Measure;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@ApplicationScoped
@Path("/measures")
@Api(value = "measures", description = "Operations about measures")
public class MeasureResource {
	
	@Inject
	private MeasureService service;
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "get a specific measure by his id", response = Measure.class)
	public Measure get(
			@PathParam("id") 
			@ApiParam(value = "the measure id", required = true)
			Long id) {
		return service.get(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "list all the measures", response = MeasureEntry.class, responseContainer="List")
	public List<MeasureEntry> list() {
		return service.list();
	}
}
