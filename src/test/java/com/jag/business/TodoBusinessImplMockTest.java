package com.jag.business;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.jag.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAMock() {
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");

		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(2, filteredStub.size());

	}

	@Test
	public void testRetrieveTodosRelatedToSpring_FirstElement() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(true, filteredStub.contains("Learn Spring MVC"));
	}

	@Test
	public void testRetrieveTodosRelatedToSpring_WithEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todos = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		assertEquals(0, filteredStub.size());
	}

	// BDD Way
	@Test
	public void testRetrieveTodosRelatedToSpring_BDDWay() {

		// Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// When
		List<String> filteredTodos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");

		// Then
		assertThat(filteredTodos.size(), is(2));

	}
	
	@Test
	public void testDeleteTodosNotRelatedToSpring_BDDWay() {

		// Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito", "Learn Latin", "Learn Latin");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

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
		
		//Declare Argument Captor
		ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
		//Define Argument Captor on specific method call

		// Given
		TodoService todoServiceMock = mock(TodoService.class);

		List<String> todos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn Mockito", "Learn Latin", "Learn Latin");

		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todos);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		// When
		todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

		// Then
		then(todoServiceMock).should(times(3)).deleteTodo(stringArgumentCaptor.capture());
		
		assertThat(stringArgumentCaptor.getAllValues().size(), is(3));
	}

}
