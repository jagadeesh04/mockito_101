package com.jag.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void letsMockListSizeMethod() {
		List<String> listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(2);
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(2, listMock.size());
	}

	@Test
	public void letsMockListSize_ReturnMultipleValues() {
		List<String> listMock = mock(List.class);
		
		when(listMock.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, listMock.size());
		assertEquals(3, listMock.size());
	}
	
	@Test
	public void letsMockListGet() {
		List<String> listMock = mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenReturn("jag");
		assertEquals("jag", listMock.get(0));
		assertEquals("jag", listMock.get(1));
	}
	
	@Test
	public void letsMockListGet_UsingBDD() {
		
		//Given
		List<String> listMock = mock(List.class);
		given(listMock.get(anyInt())).willReturn("jag");
		
		//When
		String firstElement = listMock.get(0);
		
		//Then
		assertEquals("jag", listMock.get(0));
	}
	
	@Test(expected=RuntimeException.class)
	public void letsMockList_throwAnException() {
		List<String> listMock = mock(List.class);
		//Argument Matcher
		when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));
		listMock.get(0);
	}
	
}
