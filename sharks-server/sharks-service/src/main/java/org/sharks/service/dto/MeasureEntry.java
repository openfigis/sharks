/**
 * 
 */
package org.sharks.service.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

import org.sharks.storage.domain.InformationSource;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@XmlRootElement
@Data
@AllArgsConstructor
public class MeasureEntry {

	private final long id;
	private final String symbol;
	private final String title;
	private final String documentType;
	private final Integer year;
	private final boolean binding;
	private final List<InformationSource> sources;
	
}
