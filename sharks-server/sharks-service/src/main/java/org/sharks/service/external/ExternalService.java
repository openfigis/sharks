/**
 * 
 */
package org.sharks.service.external;

import java.util.List;
import java.util.Optional;

import org.sharks.service.informea.dto.InformeaCountry;

/**
 * @author Erik van Ingen
 *
 */
public interface ExternalService {

	public List<InformeaCountry> getParties();

	public Optional<InformeaCountry> getMember(String iso3);
}
