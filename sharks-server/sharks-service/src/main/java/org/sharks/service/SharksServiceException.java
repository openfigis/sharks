package org.sharks.service;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class SharksServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1359907165992410885L;

	public SharksServiceException(String message) {
		super(message);
	}

	public SharksServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
