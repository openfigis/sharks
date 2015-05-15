/**
 * 
 */
package org.sharks.service.cache;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Retention(RUNTIME)
@Target({METHOD, FIELD})
public @interface CacheName {
	String value();
}

