/**
 * 
 */
package org.sharks.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.sharks.service.Service.ServiceType;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Singleton
public class ServicesRegistry {

	@Inject
	private Instance<Service> services;
	
	public List<String> getServices(ServiceType ... types) {
		List<String> found = new ArrayList<String>();
		for (Service service:services) {
			ServiceType type = service.type();
			System.out.println(service.name());
			if (contains(types, type)) found.add(service.name());
		}
		
		return found;
	}
	
	private boolean contains(ServiceType[] types, ServiceType type) {
		for (ServiceType t:types) if (t == type) return true;
		return false;
	}
}
