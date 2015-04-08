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
	
	public <T> void index(List<T> items, Class<T> type);
	
	public void deleteAllDocuments();

}
