/**
 * 
 */
package org.sharks.service.indexing;

import java.util.List;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public interface IndexingService {
	
	public <T> void index(List<T> items, FieldProvider<T> fieldProvider);
	
	public void deleteAllDocuments();
	
	public interface FieldProvider<T> {
		
		String getTypeName();
		
		List<String> getFieldsName();
		
		String getFieldValue(T item, String name);
	}

}
