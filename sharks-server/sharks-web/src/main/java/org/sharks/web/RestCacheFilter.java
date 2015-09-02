/**
 * 
 */
package org.sharks.web;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.sharks.service.CacheService.CacheLoadedEvent;
import org.sharks.web.resource.CacheResource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.CachingFilter;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Slf4j
@WebFilter(urlPatterns={"/rest/*"})
public class RestCacheFilter extends CachingFilter {
	
	private static final String CACHE_NAME = "restCache";
	
	@Inject
	private CacheManager cacheManager;
	
	void cacheCleaned(@Observes CacheLoadedEvent event) {
		log.info("cleaning rest cache");
		Ehcache restCache = cacheManager.getEhcache(CACHE_NAME);
		if (restCache!=null) restCache.removeAll();
		else log.warn(CACHE_NAME+" not found");
		log.info("rest cache cleaned");
	}
	
	@Override
	protected String getCacheName() {
		return CACHE_NAME;
	}

	@Override
	protected CacheManager getCacheManager() {
		return cacheManager;
	}

	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws AlreadyGzippedException,
			AlreadyCommittedException, FilterNonReentrantException, LockTimeoutException, Exception {
	
		String url = request.getRequestURL().toString();
		if (matchExcludePatterns(url)) {
		    chain.doFilter(request, response);
		    return;
		}
		
		super.doFilter(request, response, chain);
	}

	private boolean matchExcludePatterns(String url) {
		return url.contains("/rest"+CacheResource.CACHES_PATH);
	}

	@Override
	protected String calculateKey(HttpServletRequest httpRequest) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(httpRequest.getMethod()).append(httpRequest.getRequestURI()).append(httpRequest.getQueryString());
        String key = stringBuffer.toString();
        return key;
	}

}
