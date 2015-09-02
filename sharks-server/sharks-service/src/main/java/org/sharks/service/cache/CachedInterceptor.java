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
import org.sharks.service.cache.ServiceCacheManager.ServiceInfo;

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

		ServiceInfo service = getServiceInfo(ctx.getMethod().getDeclaringClass());
		
		Cached cached = getCachedAnnotation(ctx.getMethod());
		String cacheName = cached.value();
		String staticKey = cached.staticKey();
		
		Object[] parameters = ctx.getParameters();
		if (parameters.length!=1 && staticKey.isEmpty()) throw new IllegalArgumentException("Expected one parameter or static key, found "+parameters.length+" parameters in service: "+service+", cacheName: "+cacheName+", method: "+ctx.getMethod());
		
		Object key = !staticKey.isEmpty()?staticKey:parameters[0];
		
		ServiceCache<Object, Object> cache = cacheManager.getOrCreateCache(service, cacheName);
				
		CacheElement<Object> element = cache.get(key);
		
		if (element.isPresent()) return element.getValue();
		
		Object value = ctx.proceed();
		
		cache.put(key, value);
		
		return value;
	}
	
	private ServiceInfo getServiceInfo(Class<?> target) {
		Service service = target.getAnnotation(Service.class);
		if (service == null) throw new IllegalArgumentException("Missing "+Service.class.getSimpleName()+" annotation in class "+target.getClass());
		return new ServiceInfo(service.name(), service.type());
	}
	
	private Cached getCachedAnnotation(Method method) {
		Cached cached = method.getAnnotation(Cached.class);
		if (cached == null) throw new IllegalArgumentException("Missing "+Cached.class.getSimpleName()+" annotation in method "+method.getName());
		if (cached.value().isEmpty()) throw new IllegalArgumentException("Missing value attribute in "+Cached.class.getSimpleName()+" annotation in method "+method.getName());
		return cached;
	}

}
