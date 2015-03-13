/**
 * 
 */
package org.sharks.web.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	
	public Response toResponse(Exception ex) {
		ex.printStackTrace();
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode())
				.build();
	}
}
