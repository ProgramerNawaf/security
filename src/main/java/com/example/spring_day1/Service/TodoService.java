package com.example.spring_day1.Service;

import com.example.spring_day1.ApiException.ApiException;
import com.example.spring_day1.Model.Todo;
import com.example.spring_day1.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public List<Todo> getTodos(Integer userId) {
        return todoRepository.findTodosByUserId(userId);
    }

    public void addTodo(Integer userId, Todo todo) {
        todo.setUserId(userId);
        todoRepository.save(todo);
    }

    public void updateTodo(Integer userId, Todo todo, Integer todoId) {
        Todo oldTodo = todoRepository.findTodoById(todoId);

        if (oldTodo == null ){
            throw new ApiException("Todo Not found");
        }

        if (!(userId.equals(oldTodo.getUserId()))){
           throw new ApiException("Not Authorised");
        }
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);

    }

    public void deleteTodo(Integer userId, Integer todoId) {
        Todo todo = todoRepository.findTodoById(todoId);

        if (todo == null ){
            throw new ApiException("Todo Not found");
        }

        if (!(userId.equals(todo.getUserId()))){
            throw new ApiException("Not Authorised");
        }
            todoRepository.delete(todo);


    }
}
