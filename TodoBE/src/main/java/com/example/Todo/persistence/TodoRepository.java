package com.example.Todo.persistence;

import com.example.Todo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
  // 유저아이디로 검색 : SELECT * FROM Todo WHERE userId = '{userId}'
  List<TodoEntity> findByUserId(String userId);
}