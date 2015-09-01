/**
 * 
 */
package org.sharks.service.cache;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Retention(RUNTIME)
@Target({FIELD})
public @interface CacheName {
	
	/**
	 * The cache name.
	 * @return the cache name.
	 */
	String value();

}

