package com.jag.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.jag.data.api.TodoService;
import com.jag.data.api.TodoServiceStub;

public class TodoBusinessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_usingAStub() {
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(2, filteredStub.size());
	
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_FirstElement(){
		TodoService todoServiceStub = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceStub);
		
		List<String> filteredStub = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
		
		assertEquals(true, filteredStub.contains("Learn Spring MVC"));
	}

}
