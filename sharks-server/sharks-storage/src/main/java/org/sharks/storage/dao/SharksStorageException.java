package org.sharks.storage.dao;

/**
 * Generic SharksStorageException runtime exception. Vme so far does not have checked exceptions, the idea behind is
 * that checked exceptions create a lot of ugly boilerplate code, and more important, all code should be tested so that
 * exception will not occur runtime.
 * 
 * @author Erik van Ingen
 * 
 */
public class SharksStorageException extends RuntimeException {

	private static final long serialVersionUID = 1359907165992410885L;

	public SharksStorageException(String message) {
		super(message);
	}

	public SharksStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
