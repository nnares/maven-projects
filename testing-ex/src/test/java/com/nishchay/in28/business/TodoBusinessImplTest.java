package com.nishchay.in28.business;

import com.nishchay.in28.api.TodoService;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TodoBusinessImplTest {

    @Test
    public void retrieveTodosRelatedToSpringTest() {

        // Mocking the dependency object
        TodoService todoService = mock(TodoService.class);

        // defining the dependency object behaviour
        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        // Setting up stubbed responses using mocks
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);

        // injecting the dependency object mocked copy to actual object
        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        // Actual method call realObjectRef.realMethod()
        // -> internally get the response from injecting the dependency object
        List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Dummy");
        assertEquals(2, todos.size());
        assertEquals("Learn Spring MVC", todos.get(0));
        assertEquals("Learn Spring", todos.get(1));

    }

    /*
    * How to verify calls on a mock?
    * Verify how many times a method is called
    * How to capture an argument which is passed to a mock?
    * */
    @Test
    public void deleteTodosNotRelatedToSpringTest() {

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);

        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");

        Mockito.verify(todoService).deleteTodo("Learn to Dance");
        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring MVC");
        Mockito.verify(todoService, Mockito.never()).deleteTodo("Learn Spring");

        Mockito.verify(todoService, Mockito.times(1)).deleteTodo("Learn to Dance");
        Mockito.verify(todoService, Mockito.atLeast(1)).deleteTodo("Learn to Dance");
        Mockito.verify(todoService, Mockito.atLeastOnce()).deleteTodo("Learn to Dance");
        // atLeastOnce, atLeast

    }

    /*
     * How to capture an argument which is passed to a mock?
     * */
    @Test
    public void captureArgument() {
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService todoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance");
        Mockito.when(todoService.retrieveTodos("Dummy")).thenReturn(allTodos);

        TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
        todoBusinessImpl.deleteTodosNotRelatedToSpring("Dummy");
        Mockito.verify(todoService).deleteTodo(argumentCaptor.capture());

        assertEquals("Learn to Dance", argumentCaptor.getValue());
    }

}