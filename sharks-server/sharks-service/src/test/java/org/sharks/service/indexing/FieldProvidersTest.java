/**
 * 
 */
package org.sharks.service.indexing;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.sharks.service.indexing.IndexingService.FieldProvider;

import lombok.Data;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class FieldProvidersTest {
	
	@Test
	public void testByRefectionProvider() {
		
		FieldProvider<MyModel> provider = FieldProviders.byReflection(MyModel.class);
		System.out.println(provider.getFieldsName());
		
		assertEquals(5, provider.getFieldsName().size());
		
		List<String> fields = provider.getFieldsName();
		assertTrue(fields.contains("i"));
		assertTrue(fields.contains("b"));
		assertTrue(fields.contains("in"));
		assertTrue(fields.contains("bo"));
		assertTrue(fields.contains("s"));
		assertFalse(fields.contains("m"));
	}
	
	
	@Data
	public static class MyModel {
		int i;
		boolean b;
		
		Integer in;
		Boolean bo;
		
		String s;
		
		MyModel m;
	}

}
