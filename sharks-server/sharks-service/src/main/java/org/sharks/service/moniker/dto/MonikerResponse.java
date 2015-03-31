/**
 * 
 */
package org.sharks.service.moniker.dto;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
@Data
@XmlRootElement(name="moniker")
@XmlAccessorType(XmlAccessType.NONE)
public class MonikerResponse<T> {
	
	@XmlElement
	private Output<T> output;

	@XmlRootElement
	@Data
	@XmlAccessorType(XmlAccessType.NONE)
	public static class Output<T> implements Iterable<T> {
		
		@XmlAnyElement(lax=true)
		private List<T> items;

		@Override
		public Iterator<T> iterator() {
			return items.iterator();
		}
		
	}
}
