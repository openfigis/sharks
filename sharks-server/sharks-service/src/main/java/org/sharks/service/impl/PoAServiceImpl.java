/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.PoAService;
import org.sharks.service.dto.PoAEntry;
import org.sharks.storage.dao.PoADao;
import org.sharks.storage.domain.PoA;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class PoAServiceImpl implements PoAService {
	
	@Inject
	private PoADao dao;
	
	@Override
	public List<PoAEntry> list() {
		return dao.list().stream().map(m->toEntry(m)).collect(Collectors.toList());
	}

	@Override
	public List<PoAEntry> poasForCountry(String countryCode) {
		return dao.allRelatedToCountry(countryCode).stream().map(m->toEntry(m)).collect(Collectors.toList());
	}
	
	private PoAEntry toEntry(PoA poa) {
		if (poa == null) return null;
		return new PoAEntry(poa.getCode(), 
				poa.getTitle(), 
				poa.getPoAYear(), 
				poa.getPoAType(),
				poa.getStatus());
	}

	@Override
	public PoA get(Long code) {
		return dao.get(code);
	}
}
