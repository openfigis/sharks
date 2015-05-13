/**
 * 
 */
package org.sharks.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.sharks.service.PoAService;
import org.sharks.service.dto.PoAEntry;
import org.sharks.storage.dao.PoADao;
import org.sharks.storage.domain.PoA;

import static org.sharks.service.EntryConverters.*;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class PoAServiceImpl implements PoAService {
	
	@Inject
	private PoADao dao;
	
	@Override
	public PoA get(Long code) {
		return dao.get(code);
	}
	
	@Override
	public List<PoAEntry> list() {
		return convert(dao.list(), TO_POA_ENTRY);
	}

}
