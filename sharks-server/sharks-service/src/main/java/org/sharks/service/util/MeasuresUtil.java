/**
 * 
 */
package org.sharks.service.util;

import java.util.Collection;
import java.util.stream.Collectors;

import org.sharks.storage.domain.Measure;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class MeasuresUtil {
	
	public static Collection<Measure> filterReplacedMeasures(Collection<Measure> measures) {
		return measures.stream().filter((m)->m.getReplacedBy()==null).collect(Collectors.toList());
	}

}
