/**
 * 
 */
package org.sharks.service.cache;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.*;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface Cached {
	@Nonbinding String value() default "";
	@Nonbinding String staticKey() default "";
}
