package com.example.Todo.controller;

import com.example.Todo.dto.ResponseDTO;
import com.example.Todo.dto.TodoDTO;
import com.example.Todo.model.TodoEntity;
import com.example.Todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {
  @Autowired  // 자동으로 빈을 찾아 인스턴스 멤버변수에 연결
  private TodoService service;

  @GetMapping("test")
  public ResponseEntity<?> testTodo() {
    String str = service.testService();

    List<String> list = new ArrayList<>();
    list.add(str);

    ResponseDTO<String> res = ResponseDTO.<String>builder().data(list).build();
    return ResponseEntity.ok().body(res);
  }

  // Todo 아이템 생성
  @PostMapping
  public ResponseEntity<?> createTodo(@AuthenticationPrincipal String userid,
                                      @RequestBody TodoDTO dto) {
    try {
      // Dto -> TodoEntity
      TodoEntity entity = TodoDTO.toEntity(dto);
      entity.setId(null);
      entity.setUserId(userid);
      
      // 엔티티 생성
      List<TodoEntity> entities = service.create(entity);

      // 자바 스트림으로 Entity -> Dto
      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok().body(response);
    }
    catch (Exception e) {
      // 예외는 dto 대신 error에 메시지 넣어 리턴
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }

  // Todo 목록 검색
  @GetMapping
  public ResponseEntity<?> searchTodoList(@AuthenticationPrincipal String userid) {
    // 유저의 Todo 검색
    List<TodoEntity> entities = service.search(userid);

    // 자바 스트림으로 Entity -> Dto
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

    return ResponseEntity.ok().body(response);
  }

  // Todo 아이템 수정
  @PutMapping
  public ResponseEntity<?> updateTodo(@AuthenticationPrincipal String userid,
                                      @RequestBody TodoDTO dto) {
    // Dto -> TodoEntity
    TodoEntity entity = TodoDTO.toEntity(dto);
    entity.setUserId(userid);
    
    // 수정
    List<TodoEntity> entities = service.update(entity);

    // 자바 스트림으로 Entity -> Dto
    List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

    ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

    return ResponseEntity.ok().body(response);
  }

  // Todo 아이템 삭제
  @DeleteMapping
  public ResponseEntity<?> deleteTodo(@AuthenticationPrincipal String userid,
                                      @RequestBody TodoDTO dto) {
    try {
      // Dto -> TodoEntity
      TodoEntity entity = TodoDTO.toEntity(dto);
      entity.setUserId(userid);
      
      // 삭제
      List<TodoEntity> entities = service.delete(entity);

      // 자바 스트림으로 Entity -> Dto
      List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

      return ResponseEntity.ok().body(response);
    }
    catch (Exception e) {
      // 예외는 dto 대신 error에 메시지 넣어 리턴
      String error = e.getMessage();
      ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
      return ResponseEntity.badRequest().body(response);
    }
  }
}