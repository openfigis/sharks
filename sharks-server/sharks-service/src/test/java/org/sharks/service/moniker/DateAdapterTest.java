package org.sharks.service.moniker;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;
import org.sharks.service.moniker.rest.CalendarAdapter;

/**
 * @author "Federico De Faveri federico.defaveri@fao.org"
 *
 */
public class DateAdapterTest {

	private CalendarAdapter adapter = new CalendarAdapter();
	
	@Test
	public void testUnmarshallYMD() throws Exception {
		Calendar date = adapter.unmarshal("2015-06-01");
		
		assertNotNull(date);
		assertEquals(2015, date.get(Calendar.YEAR));
		assertEquals(5, date.get(Calendar.MONTH));
		assertEquals(1, date.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void testUnmarshallYM() throws Exception {
		Calendar date = adapter.unmarshal("2015-06");
		
		assertNotNull(date);
		assertEquals(2015, date.get(Calendar.YEAR));
		assertEquals(5, date.get(Calendar.MONTH));
	}
	
	@Test
	public void testUnmarshallY() throws Exception {
		Calendar date = adapter.unmarshal("2015");
		
		assertNotNull(date);
		assertEquals(2015, date.get(Calendar.YEAR));
	}
	
	@Test
	public void testUnmarshallNull() throws Exception {
		Calendar date = adapter.unmarshal(null);
		
		assertNull(date);
	}
	
	@Test
	public void testUnmarshallEmpty() throws Exception {
		Calendar date = adapter.unmarshal("");
		
		assertNull(date);
	}

}
