/**
 * 
 */
package org.sharks.web.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	
	public Response toResponse(Exception ex) {
		log.error("Exception", ex);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
				.build();
	}
}
