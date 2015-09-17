/**
 * 
 */
package org.sharks.service.cms;

import java.util.List;

import org.sharks.service.informea.dto.InformeaCountry;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface CmsService {

	public List<InformeaCountry> getParties();

	public boolean isMember(String iso3);
}
