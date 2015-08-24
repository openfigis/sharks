/**
 * 
 */
package org.sharks.service.cache.warmer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CacheWarmer {
	
	public void warmup();
	
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.FIELD})
	public @interface HighPriority {}
}
