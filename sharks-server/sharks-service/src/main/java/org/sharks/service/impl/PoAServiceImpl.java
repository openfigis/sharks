/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.sharks.service.PoAService;
import org.sharks.service.dto.PoADetails;
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
	public List<PoADetails> list() {
		return dao.list().stream().map(m->toDetails(m)).collect(Collectors.toList());
	}

	@Override
	public List<PoADetails> poasForCountry(String countryCode) {
		return dao.allRelatedToCountry(countryCode).stream().map(m->toDetails(m)).collect(Collectors.toList());
	}
	
	private PoADetails toDetails(PoA poa) {
		if (poa == null) return null;
		return new PoADetails(poa.getCode(), 
				poa.getTitle(), 
				poa.getPoAYear(), 
				poa.getPoAType(),
				poa.getStatus());
	}
}
