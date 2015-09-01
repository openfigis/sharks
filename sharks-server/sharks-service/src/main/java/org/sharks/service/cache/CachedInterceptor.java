/**
 * 
 */
package org.sharks.service.cache;

import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.sharks.service.Service;
import org.sharks.service.cache.ServiceCache.CacheElement;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Interceptor @Cached
public class CachedInterceptor {
	
	@Inject
	private ServiceCacheManager cacheManager;

	@AroundInvoke
	public Object manageCached(InvocationContext ctx) throws Exception {

		String serviceName = getServiceName(ctx.getMethod().getDeclaringClass());
		String cacheName = getCacheName(ctx.getMethod());
		
		ServiceCache<Object, Object> cache = cacheManager.getOrCreateCache(serviceName, cacheName);
		
		Object key = getKey(ctx.getParameters());
		
		CacheElement<Object> element = cache.get(key);
		
		if (element.isPresent()) return element.getValue();
		
		Object value = ctx.proceed();
		
		cache.put(key, value);
		
		return value;
	}
	
	private String getServiceName(Class<?> target) {
		Service service = target.getAnnotation(Service.class);
		if (service == null) throw new IllegalArgumentException("Missing "+Service.class.getSimpleName()+" annotation in class "+target.getClass());
		return service.name();
	}
	
	private String getCacheName(Method method) {
		Cached cached = method.getAnnotation(Cached.class);
		if (cached == null) throw new IllegalArgumentException("Missing "+Cached.class.getSimpleName()+" annotation in method "+method.getName());
		if (cached.value().isEmpty()) throw new IllegalArgumentException("Missing value attribute in "+Cached.class.getSimpleName()+" annotation in method "+method.getName());
		return cached.value();
	}
	
	private Object getKey(Object[] parameters) {
		if (parameters.length!=1) throw new IllegalArgumentException("Expected one parameter found "+parameters.length);
		return parameters[0];
	}

}
