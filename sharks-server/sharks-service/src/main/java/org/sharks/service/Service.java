/**
 * 
 */
package org.sharks.service;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Retention(RUNTIME)
@Target({ElementType.TYPE})
public @interface Service {
	
	/**
	 * The service name.
	 * @return the service name.
	 */
	String name();
	
	/**
	 * The service type.
	 * @return the service type.
	 */
	ServiceType type();
	
	public static enum ServiceType {
		INTERNAL,
		EXTERNAL;
	}
}

