package com.jag.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.jag.data.api.TodoService;

@RunWith(MockitoJUnitRunner.class)
public class TodoBusinessImplMockTestInjectMock {
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBusinessImpl todoBusinessImpl;
	
	@Captor
	ArgumentCaptor<String> stringArgumentCaptor;
	
	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);
		
		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(2, filteredStub.size());

	}

	@Test
	public void testRetrieveTodosRelatedToSpring_FirstElement() {
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(true, filteredStub.contains("Learn Spring MVC"));
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_WithEmptyList() {
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(0, filteredStub.size());
	}

	// BDD Way
	@Test
	public void testRetrieveTodosRelatedToSpring_BDDWay() {

		// Given

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		// Then
		assertThat(filteredTodos.size(), is(2));

	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_BDDWay() {

		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito", "Learn Latin", "Learn Latin");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		verify(todoServiceMock, times(2)).deleteTodo("Learn Latin");
		then(todoServiceMock).should(times(2)).deleteTodo("Learn Latin");
		verify(todoServiceMock).deleteTodo("Learn Mockito");
		then(todoServiceMock).should().deleteTodo("Learn Mockito");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring MVC");
		then(todoServiceMock).should(never()).deleteTodo("Learn Spring MVC");
	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_BDDWay_captureArgument() {
		
		// Given
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito", "Learn Latin", "Learn Latin");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		then(todoServiceMock).should(times(3)).deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(3));
	}

}
