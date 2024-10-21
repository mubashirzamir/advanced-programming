package com.workshop02.todoapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop02.todoapi.repository.TodoRepository;
import com.workshop02.todoapi.model.Todo;

@RestController
@RequestMapping("/todoitems")
public class TodoController {
	private final TodoRepository todoRepository;
	
	public TodoController(TodoRepository todoRepository) {
		this.todoRepository = todoRepository;
	}
	
	@GetMapping
	public List<Todo> getAllTodos() {
		return this.todoRepository.findAll();
	}
	
	@GetMapping("/complete")
	public List<Todo> getCompleteTodos() {
		return todoRepository.findByIsComplete(true);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
		return todoRepository.findById(id)
				.map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
