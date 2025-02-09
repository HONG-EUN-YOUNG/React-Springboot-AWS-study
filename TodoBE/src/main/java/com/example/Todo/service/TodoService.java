package com.example.Todo.service;

import com.example.Todo.model.TodoEntity;
import com.example.Todo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
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

  // Todo 아이템 생성
  public List<TodoEntity> create(final TodoEntity entity) {
    // 데이터 검증
    validate(entity);

    repository.save(entity);
    log.info("Entity Id : {} is saved", entity.getId());

    return repository.findByUserId(entity.getUserId());
  }

  // 검증부분 리팩토링
  private void validate(final TodoEntity entity) {
    if (entity == null) {
      log.warn("Entity cannot be null");
      throw new RuntimeException("Entity cannot be null");
    }

    if (entity.getUserId() == null) {
      log.warn("Unknown user");
      throw new RuntimeException("Unknown user");
    }
  }

  // Todo 목록 검색
  public List<TodoEntity> search(final String userId) {
    return repository.findByUserId(userId);
  }

  // Todo 아이템 수정 1 (Optional, Lambda 사용)
  public List<TodoEntity> update(final TodoEntity entity) {
    // 데이터 검증
    validate(entity);

    // 수정할 아이템 entity
    final Optional<TodoEntity> original = repository.findById(entity.getId());

    // original가 존재하면 새 값으로 덮어쓰기
    original.ifPresent(todo -> {
      todo.setTitle(entity.getTitle());
      todo.setDone(entity.isDone());
      
      repository.save(todo);
    });

    return search(entity.getUserId());
  }

  // Todo 아이템 수정 2 (if문 사용)
//  public List<TodoEntity> update(final TodoEntity entity) {
//    validate(entity);
//
//    final Optional<TodoEntity> original = repository.findById(entity.getId());
//
//    if(original.isPresent()) {
//      final TodoEntity todo = original.get();
//      todo.setTitle(entity.getTitle());
//      todo.setDone(entity.isDone());
//
//      repository.save(todo);
//    }
//
//    return retrieve(entity.getUserId());
//  }
  
  // Todo 아이템 삭제
  public List<TodoEntity> delete(final TodoEntity entity) {
    // 데이터 검증
    validate(entity);

    try {
      // 엔티티 삭제
      repository.delete(entity);
    }
    catch(Exception e) {
      log.error("error deleting entity ", entity.getId(), e);

      // 캡슐화를 위해 e 대신 새 exception 리턴
      throw new RuntimeException("error deleting entity " + entity.getId());
    }

    return search(entity.getUserId());
  }
}