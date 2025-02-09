package com.example.Todo.service;

import com.example.Todo.model.TodoEntity;
import com.example.Todo.persistence.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
  @Autowired
  private TodoRepository repository;

  public String testService() {
    // todo 엔티티 생성
    TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
    // 저장
    repository.save(entity);
    // 아이디 검색
    TodoEntity savedEntity = repository.findById(entity.getId()).get();
    return savedEntity.getTitle();

//    return "Test Service";
  }
}